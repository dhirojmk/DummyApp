package com.dhiroj.dummyapp.domain.repository

import com.dhiroj.dummyapp.data.model.login.LoginRequest
import com.dhiroj.dummyapp.data.model.login.LoginResponse
import com.dhiroj.dummyapp.data.model.login.RefreshRequest
import com.dhiroj.dummyapp.data.model.login.RefreshResponse
import com.dhiroj.dummyapp.data.model.login.UserResponse
import com.dhiroj.dummyapp.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun login(
        request: LoginRequest
    ): Flow<NetworkResult<LoginResponse>>

    suspend fun getCurrentUser(): UserResponse

    suspend fun refreshToken(
        request: RefreshRequest
    ): RefreshResponse
}