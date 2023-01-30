package com.example.structure.data.repository

import com.example.structure.api.Resource
import com.example.structure.api.WebService
import com.example.structure.data.repository.base.BaseRepository
import com.example.structure.data.vo.WeatherVo
import com.example.structure.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

//class WeatherRepository @Inject constructor(
//    private val webService: WebService
//) : BaseRepository() {
//    suspend fun getWeather(lat: Double, lon: Double, exclude: String, appId: String) =
//        safeApiCall { webService.getWeather(lat, lon, exclude, appId).daily }
//}

class WeatherRepository @Inject constructor(
    private val webService: WebService, @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : BaseRepository() {
    suspend fun getWeather(lat: Double, lon: Double, exclude: String, appId: String) : Flow<Resource<List<WeatherVo.DailyItem>>> = flow {
        emit(safeApiCall { webService.getWeather(lat, lon, exclude, appId).daily })
    }.flowOn(ioDispatcher)
}