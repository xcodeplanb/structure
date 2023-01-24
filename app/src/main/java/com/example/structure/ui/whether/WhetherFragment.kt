package com.example.structure.ui.whether

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.structure.MainShareViewModel
import com.example.structure.databinding.FragmentWeatherBinding
import com.example.structure.observeEvent
import com.example.structure.util.LogUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WhetherFragment : Fragment() {
    private lateinit var binding: FragmentWeatherBinding
    private val viewModel: WeatherViewModel by viewModels()
    private val mainShareViewModel: MainShareViewModel by activityViewModels()
    private lateinit var weatherAdapter: WeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LogUtil.log(TAG, ": $")
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setUpObserver()
        setUpAdapter()
    }

    private fun setUpObserver() {
        viewModel.resultList.observeEvent(viewLifecycleOwner) { list ->
            weatherAdapter.submitList(list)
        }
    }

    private fun setUpAdapter() {
        weatherAdapter = WeatherAdapter()
        binding.weatherRecyclerview.apply {
            adapter = weatherAdapter
        }
    }

    companion object {
        const val TAG = "WhetherFragment"
    }

}