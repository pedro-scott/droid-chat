package com.github.pedroscott.droidchat.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ImageResponse(
    val id: String,
    val name: String,
    val type: String,
    val url: String,
)