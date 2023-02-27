package com.example.structure.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.structure.api.Resource
import com.example.structure.data.model.GithubUser

class GithubUserPagingSource(
    private val githubRepository: GithubRepository,
    private val queryMap: HashMap<String, Any>,
    private val token: String
) : PagingSource<Int, GithubUser.ItemsItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubUser.ItemsItem> {
        val page = params.key ?: START_PAGE_INDEX
        queryMap["page"] = page

        return try {
            val response = githubRepository.searchGithubUser(token, queryMap)
            if (response is Resource.Success) {
                val names = response.value.items
                LoadResult.Page(
                    data = names,
                    prevKey = if (page == START_PAGE_INDEX) null else page - 1,
                    nextKey = if (names.isEmpty()) null else page + 1
                )
            } else {
                LoadResult.Error(Throwable(""))
            }
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GithubUser.ItemsItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val START_PAGE_INDEX = 1
    }
}