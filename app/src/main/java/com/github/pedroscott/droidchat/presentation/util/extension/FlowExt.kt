package com.github.pedroscott.droidchat.presentation.util.extension

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.StateFlow

@Composable
fun <T> StateFlow<T>.asState() = collectAsStateWithLifecycle()