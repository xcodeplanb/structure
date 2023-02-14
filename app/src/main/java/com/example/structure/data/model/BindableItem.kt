package com.example.structure.data.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.structure.BR

/**
 * obserble data model
 */

data class BindableItem (
    private var isPlaying: Boolean = false,
    private var totalTime: String = "00:00",
) : BaseObservable() {

    @Bindable
    fun getIsPlaying() = isPlaying

    fun setIsPlaying(isPlaying: Boolean) {
        this.isPlaying = isPlaying
        notifyPropertyChanged(BR.isPlaying)
    }

    @Bindable
    fun getTotalTime() = totalTime

    fun setTotalTime(totalTime: String) {
        this.totalTime = totalTime
        notifyPropertyChanged(BR.totalTime)
    }
}