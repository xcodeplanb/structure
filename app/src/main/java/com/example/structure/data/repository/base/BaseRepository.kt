package com.example.structure.data.repository.base

import com.smallticket.petping.api.Resource
import com.example.structure.data.vo.CommonResponse
import com.example.structure.data.vo.EventBus.LogoutEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.EventBus
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseRepository {
    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiCall.invoke()
                if (response is CommonResponse<*>) {
                    if (response.status == "200") {
                        return@withContext Resource.Success(response)
                    } else {
                        return@withContext Resource.Error(response.status, response.error)
                    }
                } else {
                    Resource.Failure(null, null)
                }
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        try {
                            when (throwable.code()) {
                                500 -> {
                                    Resource.Error(
                                        throwable.code().toString(),
                                        null
                                    )
                                }
                                //로그아웃
                                401 -> {
                                    EventBus.getDefault().post(LogoutEvent())
                                    Resource.Failure(null, null)
                                }

                                else -> {
                                    Resource.Failure(null, null)
                                }
                            }
                        } catch (e: Exception) {
                            Resource.Failure(null, null)
                        }
                    }
                    is UnknownHostException -> {
                        Resource.Failure(null, null)
                    }

                    is SocketTimeoutException -> {
                        Resource.Failure(null, null)
                    }

                    else -> {
                        Resource.Failure(null, null)
                    }
                }
            }
        }
    }
}