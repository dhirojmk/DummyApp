package com.dhiroj.dummyapp.utils

import com.dhiroj.dummyapp.data.model.error.NetworkError
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseApiResponseHandler {

    protected fun <T> toResultFlow(
        apiCall: suspend () -> T
    ): Flow<NetworkResult<T>> = flow {
        emit(NetworkResult.Loading)
        try {
            emit(NetworkResult.Success(apiCall()))
        } catch (e: ClientRequestException) {
            emit(
                NetworkResult.Error(
                    parseError(e)
                )
            )
        } catch (e: ServerResponseException) {
            emit(
                NetworkResult.Error(
                    parseError(e)
                )
            )
        } catch (e: Exception) {
            emit(
                NetworkResult.Error(
                    handleException(e)
                )
            )
        }
    }

    protected suspend fun parseError(
        exception: ClientRequestException
    ): NetworkError {

        return try {
            exception.response.body()
        } catch (_: Exception) {
            NetworkError(
                statusCode = exception.response.status.value,
                message = exception.message
            )
        }
    }

    protected suspend fun parseError(
        exception: ServerResponseException
    ): NetworkError {

        return try {
            exception.response.body()
        } catch (_: Exception) {
            NetworkError(
                statusCode = exception.response.status.value,
                message = exception.message
            )
        }
    }

    protected fun handleException(
        exception: Exception
    ): NetworkError {

        return NetworkError(
            message = exception.message ?: "Something went wrong"
        )
    }
}