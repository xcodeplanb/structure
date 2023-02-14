package com.example.structure.ui.paging

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.example.structure.GITHUB_TOKEN
import com.example.structure.data.repository.GithubRepository
import com.example.structure.util.LogUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class PagingViewModel @Inject constructor(private val pagingRepository: GithubRepository) :
    ViewModel(), LifecycleObserver {

    private val _retryEvent = MutableLiveData<Boolean>()
    val retryEvent: LiveData<Boolean> get() = _retryEvent

    private val _searchQuery = MutableLiveData("")
    //    private val _query = MutableSharedFlow<String>(
//        replay = 1,
//        extraBufferCapacity = 0,
//        onBufferOverflow = BufferOverflow.DROP_OLDEST
//    )
//    val query: SharedFlow<String> = _query.asSharedFlow()

    private val _query = MutableStateFlow("")
    val users: StateFlow<PagingData<PagingUiModel>> = _query.flatMapLatest { query ->
        searchMovies(query)
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), PagingData.empty()
    )

    private fun searchMovies(query: String): Flow<PagingData<PagingUiModel>> {
        return pagingRepository.searchGithubUser(
            hashMapOf("q" to query), token = GITHUB_TOKEN
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

//    fun onTextChanged(
//        s: CharSequence?,
//        start: Int,
//        before: Int,
//        count: Int
//    ) {
//
//    }
}