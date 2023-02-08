package com.example.structure.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.structure.api.WebService
import javax.inject.Inject

class GithubRepository @Inject constructor(private val webService: WebService) {
    fun searchGithubUser(map: HashMap<String, Any>, token: String) = Pager(config = PagingConfig(
        pageSize = 10, enablePlaceholders = true
    ), pagingSourceFactory = {
        GithubUserPagingSource(
            webService, map, token
        )
    }).flow

//    fun searchGithubRepositoy(map: HashMap<String, Any>, token: String) = Pager(config = PagingConfig(
//        pageSize = 10, enablePlaceholders = true
//    ), pagingSourceFactory = {
//        GithubRepositoyPagingSource(
//            webService, map, token
//        )
//    }).flow
}