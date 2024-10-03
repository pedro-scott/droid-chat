package com.github.pedroscott.droidchat.presentation.page.signup

sealed interface SignUpNavAction {
    data object Back : SignUpNavAction
}