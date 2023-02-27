package com.example.structure.data.repository.base

import com.example.structure.api.Resource
import retrofit2.HttpException

suspend fun <T> safeApiCall(apiCall: suspend () -> T ): Resource<T> {
    return try {
        Resource.Success(apiCall.invoke())
    } catch (throwable: Throwable) {
        when (throwable) {
            is HttpException -> {
                Resource.Failure(throwable.code(), throwable.response()?.errorBody())
            }
            else -> {
                Resource.Failure(null, null)
            }
        }
    }
}