package com.example.structure.data.model

data class ErrorResponse(
    val code: String,
    val title: String,
    val message: String
)

data class CommonResponse<T>(
    val status: Int,
    val data: T,
    var error: ErrorResponse
)

data class CommonListResponse<T>(
    val result: Boolean,
    val status: Int,
    val data: List<T>,
    var error: ErrorResponse
)
