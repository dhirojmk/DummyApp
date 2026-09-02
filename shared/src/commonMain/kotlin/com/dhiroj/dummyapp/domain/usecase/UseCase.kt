package com.dhiroj.dummyapp.domain.usecase

import com.dhiroj.dummyapp.data.model.Quote.QuoteResponse
import com.dhiroj.dummyapp.data.model.login.LoginRequest
import com.dhiroj.dummyapp.data.model.login.LoginResponse
import com.dhiroj.dummyapp.data.model.login.UserResponse
import com.dhiroj.dummyapp.data.model.product.Product
import com.dhiroj.dummyapp.data.model.product.ProductsResponse
import com.dhiroj.dummyapp.domain.repository.Repository
import com.dhiroj.dummyapp.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class UseCase(
    private val repository: Repository
) {

    suspend fun login(
        request: LoginRequest
    ): Flow<NetworkResult<LoginResponse>> {
        return repository.login(request)
    }

    suspend fun getCurrentUser(): UserResponse {
        return repository.getCurrentUser()
    }
    suspend operator fun invoke():Flow<NetworkResult<QuoteResponse>> {
        return repository.getQuotes()
    }
    suspend fun getProducts(): Flow<NetworkResult<ProductsResponse>> {
        return repository.getProducts()
    }

    suspend fun getProductById(
        productId: Int
    ): Flow<NetworkResult<Product>> {
        return repository.getProductById(productId)
    }
}