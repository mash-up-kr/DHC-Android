package com.dhc.dhcandroid.util

import com.dhc.common.DhcResult
import retrofit2.Response

inline fun <T> runDhcCatching(block: () -> Response<T>): DhcResult<T> {
    return try {
        val response = block()
        if (response.isSuccessful) {
            val body = response.body()
            DhcResult.Success(body)
        } else {
            DhcResult.Failure(response.code(), response.errorBody()?.string())
        }
    } catch (e: Throwable) {
        DhcResult.Exception(e)
    }
}
