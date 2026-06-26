package com.dhiroj.dummyapp.domain.usecase

import com.dhiroj.dummyapp.data.model.Quote
import com.dhiroj.dummyapp.data.model.QuoteResponse
import com.dhiroj.dummyapp.domain.QuoteRepository
import com.dhiroj.dummyapp.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class GetQuotesUseCase(
    private val repository: QuoteRepository
) {
    suspend operator fun invoke():Flow<NetworkResult<QuoteResponse>> {
        return repository.getQuotes()
    }
}