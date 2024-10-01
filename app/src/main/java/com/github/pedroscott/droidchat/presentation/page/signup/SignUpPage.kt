package com.github.pedroscott.droidchat.presentation.page.signup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.github.pedroscott.droidchat.presentation.navigation.ChatRoute
import kotlinx.serialization.Serializable

@Serializable
object SignUpRoute : ChatRoute<Nothing> {

    @Composable
    override fun Page(handleNavAction: (Nothing) -> Unit) {
        // TODO
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Em construção...")
        }
    }
}