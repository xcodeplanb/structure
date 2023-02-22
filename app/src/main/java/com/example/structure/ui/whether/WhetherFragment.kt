package com.example.structure.ui.whether

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.structure.MainShareViewModel
import com.example.structure.R
import com.example.structure.api.Resource
import com.example.structure.data.model.UserItem
import com.example.structure.data.model.Weather
import com.example.structure.databinding.FragmentWeatherBinding
import com.example.structure.ui.HomeFragmentDirections
import com.example.structure.util.LogUtil
import com.example.structure.util.repeatOnStarted
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.OnItemClickListener
import com.xwray.groupie.Section
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class WhetherFragment : Fragment() {
    private lateinit var binding: FragmentWeatherBinding
    private lateinit var weatherAdapter: WeatherAdapter
    private lateinit var groupAdapter: GroupieAdapter
    private val viewModel: WeatherViewModel by viewModels()
    private val mainShareViewModel: MainShareViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setUpObserver()
        setUpAdapter()

        LogUtil.log("TAG", ": $")
    }

    private fun setUpObserver() {
        repeatOnStarted {
            viewModel.fullList.collectLatest { data ->
                if (data is Resource.Success) {
                    groupAdapter.addAll(makeSectionList(data.value))
                }
            }
        }

//        viewModel.weatherList.observeEvent(viewLifecycleOwner) { data ->
//            if (data is Resource.Success) {
//                groupAdapter.clear()
//                groupAdapter.addAll(makeSectionList(data.value))
//            } else if (data is Resource.Loading) {
//                //To Do
//            }
//        }
    }

    private fun setUpAdapter() {
        groupAdapter = GroupieAdapter().apply {
            setOnItemClickListener(onItemClickListener)
        }
        binding.weatherRecyclerview.apply {
            layoutManager = LinearLayoutManager(binding.weatherRecyclerview.context)
            adapter = groupAdapter
        }
    }

    private fun makeSectionList(weatherVoList: List<Weather>): ArrayList<Section> {
        val sections = ArrayList<Section>()
        weatherVoList.forEach { weatherVo ->
            val section = Section()
            section.setHeader(HeaderItem(weatherVo.timezone))
            section.addAll(weatherVo.daily.map { dailyItem -> WeatherItem(dailyItem) })
            sections.add(section)
        }
        return sections
    }

    private val onItemClickListener = OnItemClickListener { item, _ ->
        if (item is WeatherItem) {
            activity?.let { activity ->
                activity.findNavController(R.id.nav_main).navigate(
                    HomeFragmentDirections.actionWeatherScreenToWeatherDetailScreen(
                        UserItem((""), (""))
                    )
                )
            }
        }
    }

    companion object {
        const val TAG = "WhetherFragment"
    }
}