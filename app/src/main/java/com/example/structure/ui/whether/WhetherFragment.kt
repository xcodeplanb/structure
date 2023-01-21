package com.example.structure.ui.whether

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.structure.MainShareViewModel
import com.example.structure.databinding.WeatherBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WhetherFragment : Fragment() {
    private lateinit var binding: WeatherBinding
    private val viewModel: WeatherViewModel by viewModels()
    private val mainShareViewModel: MainShareViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        if (this::binding.isInitialized.not()) {
            binding = WeatherBinding.inflate(inflater, container, false)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }
}