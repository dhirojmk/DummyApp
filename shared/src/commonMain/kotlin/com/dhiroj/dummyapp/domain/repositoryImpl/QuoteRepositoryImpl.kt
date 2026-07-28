package com.dhiroj.dummyapp.domain.repositoryImpl

import com.dhiroj.dummyapp.data.model.QuoteResponse
import com.dhiroj.dummyapp.data.network.RemoteApi
import com.dhiroj.dummyapp.domain.repository.QuoteRepository
import com.dhiroj.dummyapp.utils.BaseApiResponseHandler
import com.dhiroj.dummyapp.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class QuoteRepositoryImpl(
    private val api: RemoteApi
) : BaseApiResponseHandler(), QuoteRepository {

    override suspend fun getQuotes(): Flow<NetworkResult<QuoteResponse>> {
        return toResultFlow {
            api.getQuotes()
        }
    }
}