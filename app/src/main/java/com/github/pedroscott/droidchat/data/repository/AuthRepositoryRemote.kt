package com.github.pedroscott.droidchat.data.repository

import com.github.pedroscott.droidchat.data.datasource.AuthDataSource
import com.github.pedroscott.droidchat.data.datasource.PreferencesDataSource
import com.github.pedroscott.droidchat.data.model.request.SignInRequest
import com.github.pedroscott.droidchat.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryRemote @Inject constructor(
    private val dataSource: AuthDataSource,
    private val preferences: PreferencesDataSource
) : AuthRepository {

    override suspend fun signIn(username: String, password: String) {
        dataSource.signIn(
            SignInRequest(username = username, password = password)
        ).also {
            preferences.saveAccessToken(it.token)
        }
    }
}