package com.github.pedroscott.droidchat.data.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface PreferencesDataSource {
    suspend fun saveAccessToken(token: String)
    suspend fun getAccessToken(): String?
}

class PreferencesDataSourceLocal @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : PreferencesDataSource {

    override suspend fun saveAccessToken(token: String) {
        dataStore.edit {
            it[ACCESS_TOKEN] = token
        }
    }

    override suspend fun getAccessToken(): String? =
        dataStore.data.map { it[ACCESS_TOKEN] }.firstOrNull()

    private companion object {
        val ACCESS_TOKEN = stringPreferencesKey("ACCESS_TOKEN")
    }
}