package com.example.structure.data.repository

import com.example.structure.api.WebService
import com.example.structure.data.repository.base.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val webService: WebService
) : BaseRepository() {
    suspend fun getWeather(lat: Double, lon: Double, exclude: String, appId: String) =
        safeApiCall { webService.getWeather(lat, lon, exclude, appId).daily }
}