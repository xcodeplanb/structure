//package com.example.structure.data.repository
//
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import com.example.structure.api.WebService
//import com.example.structure.data.model.GithubUser
//import com.example.structure.util.LogUtil
//
//class GithubRepositoyPagingSource(
//    private val webService: WebService,
//    private val queryMap: HashMap<String, Any>,
//    private val token: String
//) : PagingSource<Int, GithubUser.ItemsItem>() {
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubUser.ItemsItem> {
//        val page = params.key ?: START_PAGE_INDEX
//        queryMap["page"] = page
//
//        return try {
//            val response = webService.searchUser(token, queryMap)
//            val names = response.items
//            val totalCount = response.totalCount
//
//            LoadResult.Page(
//                data = names,
//                prevKey = if (page == START_PAGE_INDEX) null else page - 1,
//                nextKey = if (totalCount == 0) null else page + 1
//            )
//        } catch (exception: Exception) {
//            LoadResult.Error(exception)
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, GithubUser.ItemsItem>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
//                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
//        }
//    }
//
//    companion object {
//        private const val START_PAGE_INDEX = 1
//    }
//}