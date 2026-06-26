package com.dhiroj.dummyapp.data.repositoryImpl

import com.dhiroj.dummyapp.data.model.Quote
import com.dhiroj.dummyapp.data.model.QuoteResponse
import com.dhiroj.dummyapp.data.network.QuoteApi
import com.dhiroj.dummyapp.domain.QuoteRepository
import com.dhiroj.dummyapp.utils.BaseApiResponseHandler
import com.dhiroj.dummyapp.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class QuoteRepositoryImpl(
    private val api: QuoteApi
) : BaseApiResponseHandler(), QuoteRepository {

    override suspend fun getQuotes(): Flow<NetworkResult<QuoteResponse>> {
        return toResultFlow {
            api.getQuotes()
        }
    }
}
//.copy(
//                quotes = api.getQuotes().quotes.shuffled(),
//                total = api.getQuotes().total,
//                skip = api.getQuotes().skip,
//                limit = api.getQuotes().limit
//            )