package com.example.structure.ui.paging

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.structure.R
import com.example.structure.data.model.UserItem
import com.example.structure.databinding.FragmentPagingBinding
import com.example.structure.ui.HomeFragmentDirections
import com.example.structure.util.hideSoftKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PagingFragment : Fragment() {
    private lateinit var binding: FragmentPagingBinding
    private lateinit var pagingAdapter: PagingAdapter
    private val pagingViewModel: PagingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentPagingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = pagingViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setUpAdapter()
        setUpListener()
        setUpObserver()

    }

    private fun setUpObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            pagingViewModel.users.flowWithLifecycle(
                viewLifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            ).collectLatest { data ->
                pagingAdapter.submitData(data)
            }
        }

        pagingViewModel.retryEvent.observe(requireActivity()) {
            pagingAdapter.retry()
        }
    }

    private fun setUpAdapter() {
        pagingAdapter = PagingAdapter(pagingViewModel) { item ->
            activity?.let { activity ->
                activity.findNavController(R.id.nav_main).navigate(
                    HomeFragmentDirections.actionWeatherScreenToWeatherDetailScreen(
                        UserItem((item?.login ?: ""), (item?.avatarUrl ?: ""))
                    )
                )
            }
        }

        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = pagingAdapter.withLoadStateHeaderAndFooter(
                header = PagingLoadStateAdapter(pagingViewModel),
                footer = PagingLoadStateAdapter(pagingViewModel)
            )
        }

        pagingAdapter.addLoadStateListener { loadState ->
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
//            binding.recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.emptyView.isVisible =
                loadState.source.refresh is LoadState.NotLoading
                        && loadState.append.endOfPaginationReached
                        && pagingAdapter.itemCount == 0
                        && pagingViewModel.getSearchQuery().isNotEmpty()

            //오류 메세지 작업중
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.source.refresh as? LoadState.Error
            errorState?.let {
                Toast.makeText(
                    activity,
                    "${it.error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setUpListener() {
        binding.textInputEditText.doAfterTextChanged {
            pagingViewModel.searchQuery(it.toString().trim())
        }

        binding.textInputEditText.setOnEditorActionListener { view: View, actionId: Int, _: KeyEvent? ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                view.hideSoftKeyboard()
                true
            } else {
                false
            }
        }
    }

    private suspend fun clearAdapter() {
        pagingAdapter.submitData(PagingData.empty())
    }
}