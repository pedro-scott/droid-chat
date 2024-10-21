package com.github.pedroscott.droidchat.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequest(
    val username: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val profilePictureId: String?
)