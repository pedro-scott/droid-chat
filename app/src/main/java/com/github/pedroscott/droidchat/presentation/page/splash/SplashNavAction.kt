package com.github.pedroscott.droidchat.presentation.page.splash

sealed interface SplashNavAction {
    data object SignIn : SplashNavAction
}