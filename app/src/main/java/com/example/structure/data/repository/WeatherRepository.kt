package com.example.structure.data.repository

import com.example.structure.ICON_URL
import com.example.structure.api.Resource
import com.example.structure.api.WebService
import com.example.structure.data.repository.base.BaseRepository
import com.example.structure.data.vo.WeatherVo
import com.example.structure.di.IoDispatcher
import com.example.structure.util.getDateText
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val webService: WebService, @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : BaseRepository() {

    /**
     * livedata, type recyclerView
     */
    suspend fun getWeatherWithLiveData(lat: Double, lon: Double, exclude: String, appId: String) =
        safeApiCall {
            val weatherVo = webService.getWeather(lat, lon, exclude, appId)
            weatherVo.daily.forEachIndexed { index, dailyItem ->
                if (index == 0) {
                    dailyItem.isHeaderPositon = true
                }
                dailyItem.timezoneText = getDateText(dailyItem.dt * 1000L, weatherVo.timezone)
                dailyItem.weather?.get(0)?.iconUrl =
                    ICON_URL + "${dailyItem.weather?.get(0)?.icon}.png"
                dailyItem.temp?.celsiusMin =
                    ((dailyItem.temp?.min ?: 0.0) - 273.15).toInt().toString() + "\u00B0C"
                dailyItem.temp?.celsiusMax =
                    ((dailyItem.temp?.max ?: 0.0) - 273.15).toInt().toString() + "\u00B0C"
            }
            return@safeApiCall weatherVo
        }

    /**
     * flow, groupie libraly
     */
    fun getWeatherWithFlow(
        lat: Double,
        lon: Double,
        exclude: String,
        appId: String
    ): Flow<Resource<WeatherVo>> = flow {
        emit(safeApiCall {
            val weatherVo = webService.getWeather(lat, lon, exclude, appId)
            weatherVo.daily.forEach { dailyItem ->
                dailyItem.timezoneText = getDateText(dailyItem.dt * 1000L, weatherVo.timezone)
                dailyItem.weather?.get(0)?.iconUrl =
                    ICON_URL + "${dailyItem.weather?.get(0)?.icon}.png"
                dailyItem.temp?.celsiusMin =
                    ((dailyItem.temp?.min ?: 0.0) - 273.15).toInt().toString() + "\u00B0C"
                dailyItem.temp?.celsiusMax =
                    ((dailyItem.temp?.max ?: 0.0) - 273.15).toInt().toString() + "\u00B0C"
            }
            weatherVo
        })
    }.flowOn(ioDispatcher)
}
