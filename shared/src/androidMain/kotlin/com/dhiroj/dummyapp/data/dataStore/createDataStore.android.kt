package com.dhiroj.dummyapp.data.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

lateinit var appContext: Context
actual fun createDataStore(): DataStore<Preferences> {
    return createDataStore(
        producePath = {
            appContext.filesDir.resolve(DATA_STORE_FILE_NAME).absolutePath
        }
    )
}