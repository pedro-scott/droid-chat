package com.github.pedroscott.droidchat.domain.repository

interface UserRepository {
    suspend fun uploadProfileImage(imagePath: String): String
}