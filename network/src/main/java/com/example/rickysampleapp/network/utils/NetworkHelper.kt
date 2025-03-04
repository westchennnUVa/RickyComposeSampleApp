package com.example.rickysampleapp.network.utils

sealed interface NetworkResponse<T> {
    data class Success<T>(val data: T) : NetworkResponse<T>
    data class Failure<T>(val exception: Exception) : NetworkResponse<T>

    fun onSuccess(block: (T) -> Unit): NetworkResponse<T> {
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
}

internal inline fun <T> safeApiCall(apiCall: () -> T): NetworkResponse<T> =
    try {
        NetworkResponse.Success(apiCall())
    } catch (e: Exception) {
        NetworkResponse.Failure(e)
    }
