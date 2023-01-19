package com.example.structure.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.structure.Event
import com.example.structure.UiState
import com.example.structure.data.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * android-petping-2
 * Class: LoginViewModel
 * Created by cliff on 2022/03/10.
 *
 * Description:
 */
@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var loginRepository: LoginRepository
    private val _processing = MutableStateFlow(false)
    val processing = _processing.asStateFlow()

    val uiState = MutableLiveData<Event<UiState>>()
}