package com.dhiroj.dummyapp.domain.repositoryImpl

import com.dhiroj.dummyapp.data.model.login.LoginRequest
import com.dhiroj.dummyapp.data.model.login.LoginResponse
import com.dhiroj.dummyapp.data.model.login.RefreshRequest
import com.dhiroj.dummyapp.data.model.login.RefreshResponse
import com.dhiroj.dummyapp.data.model.login.UserResponse
import com.dhiroj.dummyapp.data.network.RemoteApi
import com.dhiroj.dummyapp.domain.repository.AuthRepository
import com.dhiroj.dummyapp.utils.BaseApiResponseHandler
import com.dhiroj.dummyapp.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(
    private val remoteApi: RemoteApi
) :  BaseApiResponseHandler(),AuthRepository {
    override suspend fun login(
        request: LoginRequest
    ): Flow<NetworkResult<LoginResponse>> {
        return toResultFlow {
            remoteApi.login(request)
        }
    }

    override suspend fun getCurrentUser(): UserResponse {
        return remoteApi.getCurrentUser()
    }

    override suspend fun refreshToken(
        request: RefreshRequest
    ): RefreshResponse {
        return remoteApi.refreshToken(request)
    }

}