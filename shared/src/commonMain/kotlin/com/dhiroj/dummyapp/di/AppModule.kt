package com.dhiroj.dummyapp.di


import com.dhiroj.dummyapp.data.dataStore.createDataStore
import com.dhiroj.dummyapp.data.network.AuthApi
import com.dhiroj.dummyapp.data.network.HttpClientFactory
import com.dhiroj.dummyapp.data.network.QuoteApi
import com.dhiroj.dummyapp.data.repositoryImpl.AuthRepoImpl.AuthRepositoryImpl
import com.dhiroj.dummyapp.data.repositoryImpl.QuoteRepositoryImpl
import com.dhiroj.dummyapp.data.tokenManager.TokenManager
import com.dhiroj.dummyapp.domain.AuthRepo.AuthRepository
import com.dhiroj.dummyapp.domain.QuoteRepository
import com.dhiroj.dummyapp.domain.usecase.AuthUseCase.AuthUseCase
import com.dhiroj.dummyapp.domain.usecase.GetQuotesUseCase
import com.dhiroj.dummyapp.presentation.viewModel.AuthViewModel
import com.dhiroj.dummyapp.presentation.viewModel.QuoteViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        HttpClientFactory.create(
            baseUrl = "https://dummyjson.com",
            tokenManager = get()
        )
    }

    single {
        QuoteApi(get())
    }

    single {
        AuthApi(get(),get())
    }

    single<QuoteRepository> {
        QuoteRepositoryImpl(get())
    }
    single<AuthRepository> {
        AuthRepositoryImpl(get())
    }
    single {
        createDataStore()
    }
    single {
        TokenManager(get())
    }
    factory {
        GetQuotesUseCase(get())
    }
    factory {
        AuthUseCase(get())
    }

    viewModel {
        QuoteViewModel(get())
    }
    viewModel {
        AuthViewModel(get(), get())
    }
}