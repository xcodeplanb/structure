package com.example.structure.ui.whether

import androidx.lifecycle.*
import com.example.structure.data.model.UserItem
import kotlinx.coroutines.flow.MutableStateFlow

class WeatherDetailViewModel() :
    ViewModel(), LifecycleObserver {

    private val _userItem = MutableStateFlow<UserItem?>(null)
    val userItem get() = _userItem

    fun setUserArg(arg: UserItem) {
        userItem.value = arg
    }
}