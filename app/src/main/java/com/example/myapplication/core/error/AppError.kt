package com.example.myapplication.core.error

sealed class AppError {
    object NoInternet : AppError()
    object Timeout : AppError()
    data class HttpError(val code: Int, val message: String) : AppError()
    data class Unknown(val message: String) : AppError()
}
