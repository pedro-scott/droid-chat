package com.github.pedroscott.droidchat.presentation.page.signin

import com.github.pedroscott.droidchat.presentation.model.StringResource

data class SignInUiState(
    val isButtonLoading: Boolean = false,
    val isButtonEnabled: Boolean = false,
    val email: String = "",
    val emailError: StringResource? = null,
    val password: String = "",
    val passwordError: StringResource? = null,
    val navAction: SignInNavAction? = null
)