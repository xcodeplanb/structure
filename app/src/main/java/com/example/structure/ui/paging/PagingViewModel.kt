package com.example.structure.ui.paging

import androidx.lifecycle.*
import androidx.paging.*
import com.example.structure.data.model.GithubUser
import com.example.structure.data.repository.GithubRepository
import com.example.structure.data.repository.GithubUserPagingSource
import com.example.structure.github_token
import com.example.structure.util.LogUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class PagingViewModel @Inject constructor(private val pagingRepository: GithubRepository) :
    ViewModel(), LifecycleObserver {

    private val _retryEvent = MutableLiveData<Boolean>()
    val retryEvent: LiveData<Boolean> get() = _retryEvent

    @Inject
    lateinit var githubRepository: GithubRepository

    private val _query = MutableStateFlow("")
    val users: StateFlow<PagingData<PagingUiModel>> = _query
        .debounce(1000)
        .filter { _query.value.isNotEmpty() }
        .flatMapLatest { query ->
            searchMovies(query)
        }.stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(5000), PagingData.empty()
        )

    private fun searchMovies(query: String): Flow<PagingData<PagingUiModel>> {
        return searchGithubUser(
            hashMapOf("q" to query), token = github_token
        ).map { pagingData ->
            pagingData.map {
                PagingUiModel.UserItem(it)
            }
        }.map { pagingData ->
            pagingData.insertSeparators { before, after ->
                if (after == null || before == null) {
                    return@insertSeparators null
                }
                PagingUiModel.SeparatorItem("")
            }
        }.cachedIn(viewModelScope)
    }

    /*
    base
     */
    private fun searchGithubUser(map: HashMap<String, Any>, token: String): Flow<PagingData<GithubUser.ItemsItem>> {
        return Pager(config = PagingConfig(
            pageSize = 10, enablePlaceholders = true
        ), pagingSourceFactory = {
            GithubUserPagingSource(
                githubRepository, map, token
            )
        }).flow
    }

    fun retryEventTask() {
        _retryEvent.value = true
    }

    fun searchQuery(word: String) {
        _query.value = word
    }

//    fun onTextChanged(
//        s: CharSequence?,
//        start: Int,
//        before: Int,
//        count: Int
//    ) {
//
//    }
}