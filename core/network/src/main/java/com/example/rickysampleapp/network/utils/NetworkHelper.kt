package com.example.rickysampleapp.network.utils

import android.util.Log

sealed interface NetworkResponse<T> {
    data class Success<T>(val data: T) : NetworkResponse<T>
    data class Failure<T>(val exception: Exception) : NetworkResponse<T>

    fun onSuccess(block: (T) -> Unit): NetworkResponse<T> {
        if (this is Success) {
            block(data)
        }
        return this
    }

    suspend fun suspendOnSuccess(block: suspend (T) -> Unit): NetworkResponse<T> {
        if (this is Success) {
            block(data)
        }
        return this
    }

    fun onFailure(block: (Exception) -> Unit): NetworkResponse<T> {
        if (this is Failure) {
            block(exception)
        }
        return this
    }

    fun <R> mapResult(block: (T) -> (R)): NetworkResponse<R> =
        when (this) {
            is Success -> Success(block(data))
            is Failure -> Failure(exception)
        }
}

internal inline fun <T> safeApiCall(apiCall: () -> T): NetworkResponse<T> =
    try {
        NetworkResponse.Success(apiCall())
    } catch (e: Exception) {
        Log.d("NETWORK_FAILURE", e.message ?: "non messaget")
        NetworkResponse.Failure(e)
    }
