package com.example.structure.ui.paging

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.structure.databinding.FragmentPagingDetailBinding
import com.example.structure.util.LogUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PagingDetailFragment : Fragment() {
    private lateinit var binding: FragmentPagingDetailBinding
    private val viewModel: PagingDetailViewModel by viewModels()
    val args: PagingDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (this::binding.isInitialized.not()) {
            binding = FragmentPagingDetailBinding.inflate(inflater, container, false)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        LogUtil.log("TAG", "args: ${args.userItem?.toString()}")
        args.userItem?.let { userArg ->
            viewModel.setUserArg(userArg)
        }
    }
}