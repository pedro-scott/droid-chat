package com.github.pedroscott.droidchat.presentation.page.splash

import androidx.lifecycle.viewModelScope
import com.github.pedroscott.droidchat.presentation.page.common.ChatViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ChatViewModel<SplashUiState>(
    initUiState = SplashUiState()
) {

    init { awaitAndGo() }

    private fun awaitAndGo() {
        viewModelScope.launch {
            delay(2000L)
            updateUiState { copy(action = SplashAction.SignIn) }
        }
    }
}