package com.example.structure

sealed class UiState {
    object Loading : UiState()
    object Success : UiState()
    data class Failure(val e: Throwable? = null) : UiState()
}

