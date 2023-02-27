package com.example.structure.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.structure.api.WebService
import com.example.structure.data.model.GithubUser
import com.example.structure.util.LogUtil
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubRepository @Inject constructor(private val webService: WebService) {
    fun searchGithubUser(map: HashMap<String, Any>, token: String): Flow<PagingData<GithubUser.ItemsItem>> {
        return Pager(config = PagingConfig(
            pageSize = 10, enablePlaceholders = true
        ), pagingSourceFactory = {
            GithubUserPagingSource(
                webService, map, token
            )
        }).flow
    }

//    fun searchGithubRepositoy(map: HashMap<String, Any>, token: String) = Pager(config = PagingConfig(
//        pageSize = 10, enablePlaceholders = true
//    ), pagingSourceFactory = {
//        GithubRepositoyPagingSource(
//            webService, map, token
//        )
//    }).flow
}