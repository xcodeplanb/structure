package com.example.structure.ui.paging

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.structure.R
import com.example.structure.data.model.UserItem
import com.example.structure.databinding.FragmentPagingBinding
import com.example.structure.onEachEvent
import com.example.structure.util.LogUtil
import com.example.structure.util.hideSoftKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PagingFragment : Fragment() {
    private lateinit var binding: FragmentPagingBinding
    private lateinit var pagingAdapter: PagingAdapter
    private val pagingViewModel: PagingViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        if (this::binding.isInitialized.not()) {
            binding = FragmentPagingBinding.inflate(inflater, container, false)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (binding.lifecycleOwner == null) {
            binding.viewModel = pagingViewModel
            binding.lifecycleOwner = viewLifecycleOwner
            setUpAdapter()
            setUpListener()
            setUpObserver()
        }
    }

    private fun setUpObserver() {
//        repeatOnStarted {
//            pagingViewModel.imageItem.collectLatest { data ->
//                LogUtil.log("TAG", "imageItems: $data")
//                pagingAdapter.submitData(data)
//            }
//        }

        lifecycleScope.launch {
            pagingViewModel.userItems.onEachEvent { data ->
                pagingAdapter.submitData(data)
            }.collect()
        }

        pagingViewModel.retryEvent.observe(viewLifecycleOwner, Observer {
            pagingAdapter.retry()
        })
    }

    private fun setUpAdapter() {
        pagingAdapter = PagingAdapter(pagingViewModel) { item ->
            activity?.let { activity ->
                activity.findNavController(R.id.nav_main).navigate(
                    PagingFragmentDirections.actionPagingListToPagingDetail(
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
            LogUtil.log("TAG", "loadState: ${loadState.source}")
            binding.recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.emptyView.isVisible =
                loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && pagingAdapter.itemCount == 0

//            val errorState = loadState.source.append as? LoadState.Error
//                ?: loadState.source.prepend as? LoadState.Error
//                ?: loadState.source.refresh as? LoadState.Error
//            errorState?.let {
//                Toast.makeText(
//                    activity,
//                    "${it.error}",
//                    Toast.LENGTH_LONG
//                ).show()
//            }
        }
    }

    private fun setUpListener() {
        LogUtil.log("TAG", ": $")
        binding.textInputEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                LogUtil.log("TAG", "onTextChanged: $text")
                val searchWord = text.toString().trim()
                pagingViewModel.setSearchQuery(searchWord)

                if (searchWord == "") {
                    lifecycleScope.launch {
                        clearAdapter()
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

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