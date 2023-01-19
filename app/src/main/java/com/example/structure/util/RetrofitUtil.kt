package com.smallticket.petping.util

import com.example.structure.URL
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun <T> getErrorBody(klass: Class<T>): Converter<ResponseBody, T> {
    val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.responseBodyConverter(klass, arrayOf())
}