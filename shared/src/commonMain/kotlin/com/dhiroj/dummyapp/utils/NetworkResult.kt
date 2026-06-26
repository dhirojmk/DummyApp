package com.dhiroj.dummyapp.utils

sealed interface NetworkResult<out T> {

    data class Success<T>(
        val data: T
    ) : NetworkResult<T>

    data class Error(
        val error: NetworkError
    ) : NetworkResult<Nothing>

    data object Loading : NetworkResult<Nothing>

    data object Empty : NetworkResult<Nothing>
}