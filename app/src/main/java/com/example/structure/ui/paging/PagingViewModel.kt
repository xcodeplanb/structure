package com.example.structure.ui.paging

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.example.structure.GITHUB_TOKEN
import com.example.structure.data.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class PagingViewModel @Inject constructor(private val pagingRepository: GithubRepository) :
    ViewModel(), LifecycleObserver {

    val _retryEvent = MutableLiveData<Boolean>()
    val retryEvent: LiveData<Boolean> get() = _retryEvent

    private val _searchQuery = MutableStateFlow("")

    private var _pagingDataTT = MutableStateFlow<PagingData<PagingUiModel>>(PagingData.empty())
    val pagingDataTT: Flow<PagingData<PagingUiModel>> get() = _pagingDataTT

    val userItems =
        _searchQuery.filter {
            it.isNotEmpty()
        }.flatMapLatest {
        query ->
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
    }

    fun retryEventTask() {
        _retryEvent.value = true
    }

    fun searchQuery(word: String) {
        _searchQuery.value = word
        userItems
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