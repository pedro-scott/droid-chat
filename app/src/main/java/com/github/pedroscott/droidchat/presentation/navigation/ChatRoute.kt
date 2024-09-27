package com.github.pedroscott.droidchat.presentation.navigation

import androidx.compose.runtime.Composable

interface ChatRoute<out Action> {
    @Composable
    fun Page(handleAction: (Action) -> Unit)
}