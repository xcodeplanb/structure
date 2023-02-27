package com.example.structure

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.bumptech.glide.load.engine.Resource
import com.example.structure.util.LogUtil
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

open class Event<out T>(private val content: T) {
    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): T = content
}

fun <T> LiveData<out Event<T>>.observeEvent(owner: LifecycleOwner, onEventUnhandled: (T) -> Unit) {
    observe(owner) { it?.getContentIfNotHandled()?.let(onEventUnhandled) }
}

@OptIn(ExperimentalCoroutinesApi::class)
public suspend fun <T> Flow<T>.collectLatestNotNull(action: suspend (value: T) -> Unit) {
    mapLatest(action).buffer(0).collect()
}
