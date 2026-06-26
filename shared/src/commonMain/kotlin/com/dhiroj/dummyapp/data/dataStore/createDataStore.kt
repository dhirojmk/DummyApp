package com.dhiroj.dummyapp.data.dataStore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

const val DATA_STORE_FILE_NAME = "app.preferences_pb"

expect fun createDataStore(): DataStore<Preferences>
