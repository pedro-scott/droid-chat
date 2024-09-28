package com.github.pedroscott.droidchat.presentation.page.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class ChatViewModel<UiState>(initUiState: UiState) : ViewModel() {

    private var _uiState = MutableStateFlow(initUiState)
    val uiState: StateFlow<UiState> get() = _uiState.asStateFlow()

    protected fun updateUiState(action: UiState.() -> UiState) {
        _uiState.update { it.action() }
    }
}