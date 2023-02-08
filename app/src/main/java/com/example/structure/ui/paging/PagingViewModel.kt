package com.example.structure.ui.paging

import androidx.lifecycle.*
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.example.structure.Event
import com.example.structure.GITHUB_TOKEN
import com.example.structure.data.model.GithubUser
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

    private val _searchQuery = MutableStateFlow("")

    val userItems = _searchQuery
        .debounce(1000)
        .filter {
            it.isNotEmpty()
        }.flatMapLatest { query ->
            LogUtil.log("TAG", "query: $query")
            pagingRepository.searchGithubUser(
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
        }.map { pagingData ->
            Event(pagingData)
        }.catch {
            LogUtil.log("TAG", ": $it")
        }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun retryEventTask() {
        _retryEvent.value = true
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