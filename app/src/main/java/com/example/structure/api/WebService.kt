package com.example.structure.api

import com.example.structure.data.vo.CommonResponse
import okhttp3.RequestBody
import retrofit2.http.*

interface WebService {
    @POST("/v2/sapa/accounts/login")
    suspend fun requestSignInV2(
        @Header("Authorization") sapaAuthKey: String,
        @Header("appVersion") appVersion: String,
        @Header("osVersion") osVersion: String,
        @Header("deviceInfo") deviceInfo: String,
        @Header("mobileCarrierInfo") mobileCarrierInfo: String?,
        @Body body: RequestBody
    ): CommonResponse<Any>
}
