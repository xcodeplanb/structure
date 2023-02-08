package com.example.structure.ui.whether

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.structure.APP_ID
import com.example.structure.OPEN_WEATHER_URL
import com.example.structure.api.Resource
import com.example.structure.data.repository.WeatherRepository
import com.example.structure.data.model.Weather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {

    /**
     * with flow
     **/
    val exclude = "current,minutely,hourly,alerts"

    val seoul = weatherRepository.getWeatherWithFlow(OPEN_WEATHER_URL,
        37.5666791, 126.9782914, exclude, APP_ID
    )

    val london = weatherRepository.getWeatherWithFlow(OPEN_WEATHER_URL,
        51.509865, -0.118092, exclude, APP_ID
    )

    val chicago = weatherRepository.getWeatherWithFlow(OPEN_WEATHER_URL,
        41.8379, -87.6828, exclude, APP_ID
    )

    val fullList = combine(seoul, london, chicago) { seoul, london, chicago ->
        val combineList = arrayListOf<Weather>()
        if (seoul is Resource.Success && london is Resource.Success && chicago is Resource.Success) {
            combineList.add(seoul.value)
            combineList.add(london.value)
            combineList.add(chicago.value)
            Resource.Success(combineList.toList())
        } else {
            Resource.Failure(null, null)
        }
    }.stateIn(viewModelScope, WhileSubscribed(5000), Resource.Loading)

    /**
     * with liveData
     */
//    private val _weatherList = MutableLiveData<Event<Resource<List<WeatherVo>>>>()
//    val weatherList: LiveData<Event<Resource<List<WeatherVo>>>> get() = _weatherList
//
//    init {
//        getWeathers()
//    }
//
//    fun getWeathers() {
//        viewModelScope.launch(Dispatchers.IO) {
//            val combineList = arrayListOf<WeatherVo>()
//            val seoul = async {
//                weatherRepository.getWeatherWithLiveData(
//                    37.5666791, 126.9782914, exclude, APP_ID
//                )
//            }
//
//            val london = async {
//                weatherRepository.getWeatherWithLiveData(
//                    51.509865, -0.118092, exclude, APP_ID
//                )
//            }
//
//            val chicago = async {
//                weatherRepository.getWeatherWithLiveData(
//                    41.8379, -87.6828, exclude, APP_ID
//                )
//            }
//
//            val seoulWeather = seoul.await()
//            val londonWeather = london.await()
//            val chicagoWeather = chicago.await()
//
//            if (seoulWeather is Resource.Success && londonWeather is Resource.Success && chicagoWeather is Resource.Success) {
//                combineList.add(seoulWeather.value)
//                combineList.add(londonWeather.value)
//                combineList.add(chicagoWeather.value)
//                _weatherList.postValue(Event(Resource.Success(combineList.toList())))
//            } else {
//                Resource.Failure(null, null)
//            }
//        }
//    }

    companion object {
        const val TAG = "WhetherFragment"
    }
}

