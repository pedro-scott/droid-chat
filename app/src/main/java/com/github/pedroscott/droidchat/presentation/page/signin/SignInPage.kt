package com.github.pedroscott.droidchat.presentation.page.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.github.pedroscott.droidchat.presentation.atomic.atom.AppLogoAtom
import com.github.pedroscott.droidchat.presentation.navigation.ChatRoute
import com.github.pedroscott.droidchat.presentation.theme.ChatColors
import kotlinx.serialization.Serializable

@Serializable
object SignInRoute : ChatRoute<Nothing> {
    @Composable
    override fun Page(handleNavAction: (Nothing) -> Unit) {
        // TODO
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(ChatColors.BackgroundGradient),
            contentAlignment = Alignment.Center
        ) {
            AppLogoAtom()
        }
    }
}