package com.github.pedroscott.droidchat.presentation.page.signin

sealed interface SignInNavAction {
    data object SignUp : SignInNavAction
}