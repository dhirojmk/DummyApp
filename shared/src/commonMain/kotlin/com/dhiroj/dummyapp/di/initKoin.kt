package com.dhiroj.dummyapp.di

import org.koin.core.context.startKoin

/**
initKoin()
What?
A function that initializes the Koin dependency injection framework.
Why?
Starts Koin before the application begins using dependencies.
Use:
Called once during app startup (Android/iOS) to initialize Dependency Injection (DI) in MainActivity and MainViewController.
 **/
//fun initKoin() {
//    startKoin {
//        /**
//        startKoin {}
//        What?
//        The entry point for starting the Koin framework.
//        Why?
//        Creates the Koin container and prepares it to manage dependencies.
//        Use:
//        Initializes Koin and loads all required configurations.
//         **/
//        modules(appModule)
//        /**
//        modules(appModule)
//        What?
//        Registers the application's Koin module(s).
//        Why?
//        Tells Koin where the dependency definitions are located.
//        Use:
//        Loads all dependencies (e.g., repositories, APIs, ViewModels, use cases, DataStore, TokenManager) defined inside appModule.
//        2. appModule
//        What?
//        A Koin module containing all dependency definitions.
//        Why?
//        Centralizes dependency creation and management.
//        Use:
//        Defines how objects are created using single, factory, and viewModel.
//        Makes dependencies available throughout the application.
//         **/
//    }
//}

fun initKoin(
) {
    startKoin {
        modules(appModule)
    }
}