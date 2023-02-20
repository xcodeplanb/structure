package com.example.structure.ui.paging

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.example.structure.data.repository.GithubRepository
import com.example.structure.github_token
import com.example.structure.util.LogUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class PagingViewModel @Inject constructor(private val pagingRepository: GithubRepository) :
    ViewModel(), LifecycleObserver {

    private val _retryEvent = MutableLiveData<Boolean>()
    val retryEvent: LiveData<Boolean> get() = _retryEvent

    private val _query = MutableStateFlow("")
    val users: StateFlow<PagingData<PagingUiModel>> = _query
        .debounce(1000)
        .flatMapLatest { query ->
            if (query.isNotEmpty()) {
                searchMovies(query)
            } else {
                flowOf(PagingData.empty())
            }
        }.stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(5000), PagingData.empty()
        )

    private fun searchMovies(query: String): Flow<PagingData<PagingUiModel>> {
        return pagingRepository.searchGithubUser(
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

    fun retryEventTask() {
        _retryEvent.value = true
    }

    fun searchQuery(word: String) {
        _query.value = word
    }

    fun getSearchQuery(): String {
        return _query.value
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