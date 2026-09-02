package com.dhiroj.dummyapp.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dhiroj.dummyapp.data.model.Quote.QuoteResponse
import com.dhiroj.dummyapp.data.model.login.LoginRequest
import com.dhiroj.dummyapp.data.model.product.Product
import com.dhiroj.dummyapp.data.model.product.ProductsResponse
import com.dhiroj.dummyapp.data.tokenManager.TokenManager
import com.dhiroj.dummyapp.domain.usecase.UseCase
import com.dhiroj.dummyapp.utils.AuthUiState
import com.dhiroj.dummyapp.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ViewModel(
    private val useCase: UseCase, private val tokenManager: TokenManager

) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()
    fun clearError() {
        _uiState.update {
            it.copy(
                error = null
            )
        }
    }

    fun login(
        username: String, password: String
    ) {
        viewModelScope.launch {
            useCase.login(LoginRequest(username, password)).collect { result ->
                when (result) {
                    NetworkResult.Loading -> {
                        _uiState.update {
                            it.copy(isLoading = true, error = null)
                        }
                    }

                    is NetworkResult.Success -> {
                        tokenManager.saveAccessToken(result.data.accessToken)
                        _uiState.update {
                            it.copy(
                                isLoading = false, isLoginSuccess = true
                            )
                        }
                    }

                    is NetworkResult.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false, error = result.error.message
                            )
                        }
                    }

                    NetworkResult.Empty -> Unit
                }
            }
        }
    }

    fun getCurrentUser() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true, error = null
                )
            }
            try {
                val user = useCase.getCurrentUser()
                _uiState.update {
                    it.copy(
                        isLoading = false, user = user
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false, error = e.message
                    )
                }

            }

        }
    }

    init {
        getQuotes()
    }

    private val _getQuoteResponseState = MutableStateFlow<NetworkResult<QuoteResponse>>(
        NetworkResult.Empty
    )
    val getQuoteResponseState: StateFlow<NetworkResult<QuoteResponse>> =
        _getQuoteResponseState.asStateFlow()

    fun getQuotes(
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase().collect { result ->
                _getQuoteResponseState.value = result

            }
        }
    }
    private val _getProductsResponseState = MutableStateFlow<NetworkResult<ProductsResponse>>(NetworkResult.Empty)
    val getProductsResponseState: StateFlow<NetworkResult<ProductsResponse>> = _getProductsResponseState.asStateFlow()
    fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getProducts().collect { result ->
                _getProductsResponseState.value = result
            }
        }
    }

    private val _getProductByIdResponseState = MutableStateFlow<NetworkResult<Product>>(NetworkResult.Empty)
    val getProductByIdResponseState: StateFlow<NetworkResult<Product>> = _getProductByIdResponseState.asStateFlow()
    fun getProductById(
        productId: Int
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getProductById(productId).collect { result ->
                _getProductByIdResponseState.value = result
            }
        }
    }
}