package com.example.structure.ui.whether

import androidx.lifecycle.*
import com.example.structure.data.model.UserItem
import kotlinx.coroutines.flow.MutableStateFlow

class WeatherDetailViewModel() :
    ViewModel(), LifecycleObserver {

    val userItem = MutableStateFlow<UserItem?>(null)

    fun setUserArg(arg: UserItem) {
        userItem.value = arg
    }
}