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
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class PagingViewModel @Inject constructor(private val pagingRepository: GithubRepository) :
    ViewModel(), LifecycleObserver {

    private val _retryEvent = MutableLiveData<Boolean>()
    val retryEvent: LiveData<Boolean> get() = _retryEvent

    /**
     * MutableSharedFlow 사용시
     * [query].distinctUntilChanged() 추가
     */

//    private val query = MutableSharedFlow<String>(
//        replay = 0,
//        extraBufferCapacity = 1,
//        onBufferOverflow = BufferOverflow.DROP_OLDEST
//    )

    private val query = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val searchList: Flow<PagingData<PagingUiModel>> = query
        .filter {
            it.isNotEmpty()
        }
        .flatMapLatest { query ->
            pagingRepository.searchGithubUser(
                hashMapOf("q" to query), token = github_token
            )
        }.map { pagingData ->
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

    fun retryEventTask() {
        _retryEvent.value = true
    }

    fun searchQuery(word: String) {
        query.value = word
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