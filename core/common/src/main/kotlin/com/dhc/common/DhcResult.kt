package com.dhc.common

sealed class DhcResult<out T> {
    data class Success<T>(val data: T?): DhcResult<T>()
    data class Failure(val code: Int, val message: String?): DhcResult<Nothing>()
    data class Exception(val throwable: Throwable): DhcResult<Nothing>()
}

inline fun <T> DhcResult<T>.onSuccess(action: (T?) -> Unit): DhcResult<T> {
    if (this is DhcResult.Success) {
        action(data)
    }
    return this
}

inline fun <T> DhcResult<T>.onFailure(action: (Int, String?) -> Unit): DhcResult<T> {
    if (this is DhcResult.Failure) {
        action(code, message)
    }
    return this
}

inline fun <T> DhcResult<T>.onException(action: (Throwable) -> Unit): DhcResult<T> {
    if (this is DhcResult.Exception) {
        action(throwable)
    }
    return this
}

fun <T> DhcResult<T>.getSuccessOrNull() = if (this is DhcResult.Success) this.data else null
