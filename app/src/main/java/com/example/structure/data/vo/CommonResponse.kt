package com.example.structure.data.vo

data class ErrorResponse(
    val code: String,
    val title: String,
    val message: String
)

data class CommonResponse<T>(
    val status: String,
    val data: T,
    var error: ErrorResponse
)

data class CommonListResponse<T>(
    val result: Boolean,
    val status: String,
    val data: List<T>,
    var error: ErrorResponse
)


