package com.github.pedroscott.droidchat.domain.repository

interface AuthRepository {
    suspend fun signIn(username: String, password: String)
}