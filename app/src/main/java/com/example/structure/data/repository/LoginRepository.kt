package com.example.structure.data.repository

import com.example.structure.api.WebService
import com.example.structure.data.repository.base.BaseRepository
import android.os.Build
import okhttp3.RequestBody
import javax.inject.Inject

/**
 * android-petping-2
 * Class: LoginRepository
 * Created by cliff on 2022/03/10.
 *
 * Description:
 */
class LoginRepository @Inject constructor(private val webService: WebService) : BaseRepository() {
    suspend fun requestSignInV2(sapaKey:String, body: RequestBody,mobileCarrierInfo:String?) = safeApiCall {
        webService.requestSignInV2(sapaKey, "BuildConfig.VERSION_NAME",
            Build.VERSION.RELEASE,
            Build.MODEL,
            mobileCarrierInfo,
            body)
    }
}