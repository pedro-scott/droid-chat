package com.github.pedroscott.droidchat.presentation.page.splash

sealed interface SplashAction {
    data object SignIn : SplashAction
}