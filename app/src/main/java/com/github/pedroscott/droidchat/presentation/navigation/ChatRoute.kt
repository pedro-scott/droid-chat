package com.github.pedroscott.droidchat.presentation.navigation

import androidx.compose.runtime.Composable

interface ChatRoute<out NavAction> {
    @Composable
    fun Page(handleNavAction: (NavAction) -> Unit)
}