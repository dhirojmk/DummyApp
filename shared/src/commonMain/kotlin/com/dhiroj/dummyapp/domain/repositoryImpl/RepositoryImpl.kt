package com.dhiroj.dummyapp.domain.repositoryImpl

import com.dhiroj.dummyapp.data.model.Quote.QuoteResponse
import com.dhiroj.dummyapp.data.model.login.LoginRequest
import com.dhiroj.dummyapp.data.model.login.LoginResponse
import com.dhiroj.dummyapp.data.model.login.UserResponse
import com.dhiroj.dummyapp.data.model.product.Product
import com.dhiroj.dummyapp.data.model.product.ProductsResponse
import com.dhiroj.dummyapp.data.network.RemoteApi
import com.dhiroj.dummyapp.domain.repository.Repository
import com.dhiroj.dummyapp.utils.BaseApiResponseHandler
import com.dhiroj.dummyapp.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val remoteApi: RemoteApi
) :  BaseApiResponseHandler(),Repository {
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
    override suspend fun getQuotes(): Flow<NetworkResult<QuoteResponse>> {
        return toResultFlow {
            remoteApi.getQuotes()
        }
    }

    override suspend fun getProducts(): Flow<NetworkResult<ProductsResponse>> {
        return toResultFlow {
            remoteApi.getProducts()
        }
    }

    override suspend fun getProductById(productId: Int): Flow<NetworkResult<Product>> {
        return toResultFlow {
            remoteApi.getProductById(productId)
        }    }


}