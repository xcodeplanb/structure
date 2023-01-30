package com.example.structure.ui.whether

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.structure.APP_ID
import com.example.structure.Event
import com.example.structure.api.Resource
import com.example.structure.data.repository.WeatherRepository
import com.example.structure.data.vo.WeatherVo
import com.example.structure.util.LogUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {
    private val _resultList = MutableLiveData<Event<List<WeatherVo.DailyItem>>>()
    val resultList: LiveData<Event<List<WeatherVo.DailyItem>>>
        get() = _resultList
    val weatherlist = arrayListOf<WeatherVo.DailyItem>()

    val result = flow {
        withContext(Dispatchers.IO) {
            val seoulList = async {
                val data = weatherRepository.getWeather(
                    37.5666791, 126.9782914, "current,minutely,hourly,alerts", APP_ID
                )

                if (data is Resource.Success) {
                    LogUtil.log(TAG, ": $")
                }
            }
            val londonList = async {
                val data = weatherRepository.getWeather(
                    51.509865, -0.118092, "current,minutely,hourly,alerts", APP_ID
                )

                if (data is Resource.Success) {
                    LogUtil.log(TAG, ": $")
                }
            }
            val chicagolList = async {
                val data = weatherRepository.getWeather(
                    41.8379, -87.6828, "current,minutely,hourly,alerts", APP_ID
                )

                if (data is Resource.Success) {
                    LogUtil.log(TAG, ": $")
                }
            }
        }
        emit(weatherlist)
    }.stateIn(
        scope = viewModelScope, started = SharingStarted.WhileSubscribed(5000L), initialValue = 0
    )

//    init {
//        getWeatherAll()
//    }
//
//    // Seoul, London, Chicago 날씨 가져오기
//    private fun getWeatherAll() {
//        LogUtil.log("TAG", ": $")
//        viewModelScope.launch {
//            val weatherlist = arrayListOf<WeatherVo.DailyItem>()
//            var seoulWeathers = listOf<WeatherVo.DailyItem>()
//            var londonWeathers = listOf<WeatherVo.DailyItem>()
//            var chicagoWeathers = listOf<WeatherVo.DailyItem>()
//            val deferred = listOf(async {
//                val response = weatherRepository.getWeather(
//                    37.5666791, 126.9782914, "current,minutely,hourly,alerts", APP_ID
//                )
//                when (response) {
//                    is Resource.Success -> {
//                        response.value.let {
//                            val timeZone = it.timezone
//                            seoulWeathers = it.daily
//                            seoulWeathers.forEach { dailyItem ->
//                                dailyItem.timezoneText = timeZone
//                            }
//                        }
//                    }
//                    else -> {
//                        //do Nothing
//                    }
//                }
//            }, async {
//                val response = weatherRepository.getWeather(
//                    51.509865, -0.118092, "current,minutely,hourly,alerts", APP_ID
//                )
//                when (response) {
//                    is Resource.Success -> {
//                        response.value.let {
//                            val timeZone = it.timezone
//                            londonWeathers = it.daily
//                            londonWeathers.forEach { dailyItem ->
//                                dailyItem.timezoneText = timeZone
//                            }
//                        }
//                    }
//                    else -> {
//                        //do Nothing
//                    }
//                }
//            }, async {
//                val response = weatherRepository.getWeather(
//                    41.8379, -87.6828, "current,minutely,hourly,alerts", APP_ID
//                )
//                when (response) {
//                    is Resource.Success -> {
//                        response.value.let { it ->
//                            val timeZone = it.timezone
//                            chicagoWeathers = it.daily
//                            chicagoWeathers.forEach { dailyItem ->
//                                dailyItem.timezoneText = timeZone
//                            }
//                        }
//                    }
//                    else -> {
//                        //do Nothing
//                    }
//                }
//            })
//
//            deferred.awaitAll()
//
//            weatherlist.add(WeatherVo.DailyItem(cityName = "Seoul", isHeader = true))
//            weatherlist.addAll(seoulWeathers.dropLast(2))
//            weatherlist.add(WeatherVo.DailyItem(cityName = "London", isHeader = true))
//            weatherlist.addAll(londonWeathers.dropLast(2))
//            weatherlist.add(WeatherVo.DailyItem(cityName = "Chicago", isHeader = true))
//            weatherlist.addAll(chicagoWeathers.dropLast(2))
//
//            _resultList.postValue(Event(weatherlist))
//        }

    companion object {
        const val TAG = "WhetherFragment"
    }
}

