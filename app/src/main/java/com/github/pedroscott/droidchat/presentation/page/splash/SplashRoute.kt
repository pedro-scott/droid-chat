package com.github.pedroscott.droidchat.presentation.page.splash

import androidx.compose.runtime.Composable
import com.github.pedroscott.droidchat.presentation.atomic.template.SplashTemplate
import com.github.pedroscott.droidchat.presentation.navigation.ChatRoute
import kotlinx.serialization.Serializable

@Serializable
object SplashRoute : ChatRoute<Nothing> {
    @Composable
    override fun Page(handleAction: (Nothing) -> Unit) {
        SplashTemplate()
    }
}