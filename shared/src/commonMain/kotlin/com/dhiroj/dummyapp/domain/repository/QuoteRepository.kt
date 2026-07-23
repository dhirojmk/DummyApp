package com.dhiroj.dummyapp.domain.repository

import com.dhiroj.dummyapp.data.model.QuoteResponse
import com.dhiroj.dummyapp.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {
    suspend fun getQuotes(): Flow<NetworkResult<QuoteResponse>>

}