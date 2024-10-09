package com.github.pedroscott.droidchat.presentation.page.signup

sealed interface SignUpAction {
    data object Camera : SignUpAction
    data object Gallery : SignUpAction
    sealed interface Nav : SignUpAction {
        data object Back : Nav
    }
}