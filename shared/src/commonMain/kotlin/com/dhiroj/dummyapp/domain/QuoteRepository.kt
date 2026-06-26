package com.dhiroj.dummyapp.domain

import com.dhiroj.dummyapp.data.model.Quote
import com.dhiroj.dummyapp.data.model.QuoteResponse
import com.dhiroj.dummyapp.data.network.QuoteApi
import com.dhiroj.dummyapp.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {
    suspend fun getQuotes(): Flow<NetworkResult<QuoteResponse>>

}