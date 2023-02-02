package com.example.structure.api

import com.example.structure.data.vo.WeatherVo
import retrofit2.http.*

interface WebService {
    @GET("/data/3.0/onecall")
    suspend fun getWeatherWithLivedata(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("exclude") exclude: String,
        @Query("appid") appid: String,
    ): WeatherVo

    @GET("/data/3.0/onecall")
    fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("exclude") exclude: String,
        @Query("appid") appid: String,
    ): WeatherVo
}

