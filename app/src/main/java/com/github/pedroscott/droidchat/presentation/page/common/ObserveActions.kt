package com.github.pedroscott.droidchat.presentation.page.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

@Composable
fun <T> ObserveActions(
    actionFlow: Flow<T>,
    handleAction: (T) -> Unit
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(lifecycle, actionFlow) {
        withContext(Dispatchers.Main.immediate) {
            actionFlow
                .flowWithLifecycle(lifecycle)
                .collect { handleAction(it) }
        }
    }
}