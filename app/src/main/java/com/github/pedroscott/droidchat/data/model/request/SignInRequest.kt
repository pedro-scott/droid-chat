package com.github.pedroscott.droidchat.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class SignInRequest(
    val username: String,
    val password: String
)