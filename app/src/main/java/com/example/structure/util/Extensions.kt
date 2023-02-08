package com.example.structure.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 *
 * repeatOnLifecycle multiple
 * flowWithLifecycle => single
 */
//fun Fragment.repeatOnStarted(block: suspend CoroutineScope.() -> Unit) {
//    viewLifecycleOwner.lifecycleScope.launch {
//        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED, block)
//    }
//}

fun LifecycleOwner.repeatOnStarted(block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED, block)
    }
}

//fun <T> LifecycleOwner.collectLast(flow: Flow<T>, action: suspend (T) -> Unit) {
//    lifecycleScope.launch {
//        repeatOnLifecycle(Lifecycle.State.STARTED) {
//            flow.collectLatest(action)
//        }
//    }
//}
//
//fun <T> LifecycleOwner.collect(flow: Flow<T>, action: suspend (T) -> Unit) {
//    lifecycleScope.launch {
//        repeatOnLifecycle(Lifecycle.State.STARTED) {
//            flow.collect {
//                action.invoke(it)
//            }
//        }
//    }
//}