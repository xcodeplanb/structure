package com.example.structure.data.repository

import com.example.structure.api.Resource
import com.example.structure.api.WebService
import com.example.structure.data.model.GithubUser
import com.example.structure.data.repository.base.BaseRepository
import com.example.structure.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GithubRepository @Inject constructor(
    private val webService: WebService, @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : BaseRepository() {
    suspend fun searchGithubUser(token: String, map: HashMap<String, Any>): Resource<GithubUser> =
        safeApiCall {
            webService.searchUser(token, map)
        }
}