package com.dhiroj.dummyapp.di


import com.dhiroj.dummyapp.data.dataStore.createDataStore
import com.dhiroj.dummyapp.data.network.RemoteApi
import com.dhiroj.dummyapp.data.network.HttpClientFactory
import com.dhiroj.dummyapp.domain.repositoryImpl.RepositoryImpl
import com.dhiroj.dummyapp.data.tokenManager.TokenManager
import com.dhiroj.dummyapp.domain.repository.Repository
import com.dhiroj.dummyapp.domain.usecase.UseCase
import com.dhiroj.dummyapp.presentation.viewModel.ViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        HttpClientFactory.create(
            baseUrl = "https://dummyjson.com", tokenManager = get()
        )
    }
    single {
        RemoteApi(get())
    }
    single<Repository> {
        RepositoryImpl(get())
    }
    single {
        createDataStore()
    }
    single {
        TokenManager(get())
    }
    factory {
        UseCase(get())
    }
    viewModel {
        ViewModel(get(), get())
    }
}