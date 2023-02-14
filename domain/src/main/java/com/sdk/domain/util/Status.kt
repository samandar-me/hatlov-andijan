package com.sdk.domain.util

sealed class Status<out T> {
    object Loading: Status<Nothing>()
    data class Error(val message: String): Status<Nothing>()
    data class Success<out T>(val data: T): Status<T>()
}