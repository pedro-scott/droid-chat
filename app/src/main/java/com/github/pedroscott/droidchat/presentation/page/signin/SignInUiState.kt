package com.github.pedroscott.droidchat.presentation.page.signin

import androidx.compose.runtime.Immutable
import com.github.pedroscott.droidchat.presentation.model.StringResource

@Immutable
data class SignInUiState(
    val isButtonLoading: Boolean = false,
    val isButtonEnabled: Boolean = false,
    val email: String = "",
    val emailError: StringResource? = null,
    val password: String = "",
    val passwordError: StringResource? = null
)