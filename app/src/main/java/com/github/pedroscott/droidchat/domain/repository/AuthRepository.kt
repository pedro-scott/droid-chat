package com.github.pedroscott.droidchat.domain.repository

interface AuthRepository {
    suspend fun signUp(
        username: String,
        password: String,
        firstName: String,
        lastName: String,
        profilePictureId: String?,
    )
    suspend fun signIn(username: String, password: String)
}