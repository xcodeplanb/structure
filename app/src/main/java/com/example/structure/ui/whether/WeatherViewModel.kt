package com.example.structure.ui.whether

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.structure.Event
import com.example.structure.UiState
import com.example.structure.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var loginRepository: WeatherRepository
    private val _processing = MutableStateFlow(false)
    val processing = _processing.asStateFlow()

    val uiState = MutableLiveData<Event<UiState>>()
}