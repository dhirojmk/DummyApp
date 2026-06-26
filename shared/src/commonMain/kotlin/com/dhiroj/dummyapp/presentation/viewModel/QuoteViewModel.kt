package com.dhiroj.dummyapp.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dhiroj.dummyapp.data.model.QuoteResponse
import com.dhiroj.dummyapp.domain.usecase.GetQuotesUseCase
import com.dhiroj.dummyapp.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class QuoteViewModel(
    private val getQuotesUseCase: GetQuotesUseCase
) : ViewModel() {

    init {
        getQuotes()
    }
private val _getQuoteResponseState =
    MutableStateFlow<NetworkResult<QuoteResponse>>(
        NetworkResult.Empty
    )
    val getQuoteResponseState: StateFlow<NetworkResult<QuoteResponse>> =
        _getQuoteResponseState.asStateFlow()
    fun getQuotes(
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            getQuotesUseCase().collect { result ->
                _getQuoteResponseState.value = result

            }
        }
    }
}