package com.dhiroj.dummyapp.di


import com.dhiroj.dummyapp.data.dataStore.createDataStore
import com.dhiroj.dummyapp.data.network.RemoteApi
import com.dhiroj.dummyapp.data.network.HttpClientFactory
import com.dhiroj.dummyapp.domain.repositoryImpl.AuthRepositoryImpl
import com.dhiroj.dummyapp.domain.repositoryImpl.QuoteRepositoryImpl
import com.dhiroj.dummyapp.data.tokenManager.TokenManager
import com.dhiroj.dummyapp.domain.repository.AuthRepository
import com.dhiroj.dummyapp.domain.repository.QuoteRepository
import com.dhiroj.dummyapp.domain.usecase.AuthUseCase
import com.dhiroj.dummyapp.domain.usecase.GetQuotesUseCase
import com.dhiroj.dummyapp.presentation.viewModel.AuthViewModel
import com.dhiroj.dummyapp.presentation.viewModel.QuoteViewModel
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

/**
single {}
What?
Creates one instance of a class.
Why?
When the same object should be shared throughout the application.
Use:
HttpClient
Repository
API
Database
DataStore
TokenManager
Lifecycle:
Created once.
Reused everywhere.
Destroyed when Koin is stopped (usually when the app closes).
 **/


/**
factory {}

What?

Creates a new instance every time it is requested.

Why?

When the object doesn't need to be shared.

Use:

Use Cases
Validators
Helpers
Mappers

Lifecycle:

New object on every get().
Not reused.
 **/

/***
viewModel {}

What?

Registers a ViewModel with Koin.

Why?

Lets Koin create and manage ViewModels according to the screen lifecycle.

Use:

HomeViewModel
LoginViewModel
ProfileViewModel

Lifecycle:

Created when the screen needs it.
Survives configuration changes (on Android).
Destroyed when the ViewModel is cleared.
 ***/

//scoped {}
//One instance within a specific scope (e.g., login session, feature, or screen flow)
//Note: scoped {} is used less frequently than the other three and is mainly useful when you want an object to live only within a defined scope instead of the whole app.
//scoped → One instance per defined scope.
