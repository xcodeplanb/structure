package com.example.structure.data.repository.base

import com.example.structure.api.Resource
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseRepository {
    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
        return try {
            Resource.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException, is UnknownHostException, is SocketTimeoutException -> {
                    Resource.Failure(null, null)
                }
                else -> {
                    Resource.Failure(null, null)
                }
            }
        }
    }
}