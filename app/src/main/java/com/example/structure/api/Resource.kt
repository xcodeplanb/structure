package com.example.structure.api

import com.example.structure.data.vo.ErrorResponse
import okhttp3.ResponseBody

sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()

    data class Failure(
        val errorCode: Int?, val errorBody: ResponseBody?
    ) : Resource<Nothing>()

    data class Error(
        val errorCode: String?, val errorBody: ErrorResponse?
    ) : Resource<Nothing>()
}

