package com.example.structure.data.repository

import com.example.structure.api.WebService
import com.example.structure.data.repository.base.BaseRepository
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val webService: WebService
) : BaseRepository() {
    suspend fun getWeather(lat: Double, lon: Double, exclude: String, appId: String) =
        safeApiCall { webService.getWeather(lat, lon, exclude, appId) }
}