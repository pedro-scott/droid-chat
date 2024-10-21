package com.github.pedroscott.droidchat.presentation.page.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.pedroscott.droidchat.domain.entity.error.AppError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class ChatViewModel<UiState, Action>(initUiState: UiState) : ViewModel() {

    private var _uiState = MutableStateFlow(initUiState)
    val uiState: StateFlow<UiState> get() = _uiState.asStateFlow()

    private var _action = Channel<Action>()
    val action = _action.receiveAsFlow()

    protected fun updateUiState(action: UiState.() -> UiState) {
        _uiState.update { it.action() }
    }

    protected fun sendAction(action: Action) {
        viewModelScope.launch {
            _action.send(action)
        }
    }

    protected fun <T> executeAsync(
        block: suspend CoroutineScope.() -> Result<T>,
        onLoading: (Boolean) -> Unit = {},
        onSuccess: (T) -> Unit = {},
        onError: (AppError?) -> Unit = {}
    ) {
        viewModelScope.launch {
            onLoading(true)
            block()
                .onSuccess {
                    onLoading(false)
                    onSuccess(it)
                }
                .onFailure {
                    onLoading(false)
                    onError(it as? AppError)
                }
        }
    }
}