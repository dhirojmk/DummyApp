package com.dhiroj.dummyapp.domain.repository

import com.dhiroj.dummyapp.data.model.Quote.QuoteResponse
import com.dhiroj.dummyapp.data.model.login.LoginRequest
import com.dhiroj.dummyapp.data.model.login.LoginResponse
import com.dhiroj.dummyapp.data.model.login.UserResponse
import com.dhiroj.dummyapp.data.model.product.Product
import com.dhiroj.dummyapp.data.model.product.ProductsResponse
import com.dhiroj.dummyapp.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun login(
        request: LoginRequest
    ): Flow<NetworkResult<LoginResponse>>

    suspend fun getCurrentUser(): UserResponse
    suspend fun getQuotes(): Flow<NetworkResult<QuoteResponse>>

    suspend fun getProducts(): Flow<NetworkResult<ProductsResponse>>

    suspend fun getProductById(
        productId: Int
    ): Flow<NetworkResult<Product>>


}