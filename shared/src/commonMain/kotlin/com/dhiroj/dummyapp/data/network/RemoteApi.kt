package com.dhiroj.dummyapp.data.network

import com.dhiroj.dummyapp.data.model.QuoteResponse
import com.dhiroj.dummyapp.data.model.login.LoginRequest
import com.dhiroj.dummyapp.data.model.login.LoginResponse
import com.dhiroj.dummyapp.data.model.login.RefreshRequest
import com.dhiroj.dummyapp.data.model.login.RefreshResponse
import com.dhiroj.dummyapp.data.model.login.UserResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class RemoteApi(
    private val client: HttpClient,
) {
    suspend fun login(
        request: LoginRequest
    ): LoginResponse {
        return client.post(ApiEndpoints.LOGIN) {
            setBody(request)
        }.body()
    }
    suspend fun getCurrentUser(): UserResponse {
        return client.get(ApiEndpoints.CURRENT_USER) {
        }.body()
    }

    suspend fun refreshToken(
        request: RefreshRequest
    ): RefreshResponse {
        return client
            .post(ApiEndpoints.REFRESH_TOKEN) {
                setBody(request)
            }
            .body()
    }
    suspend fun getQuotes(): QuoteResponse {
        return client
            .get(ApiEndpoints.QUOTES)
            .body()
    }
}