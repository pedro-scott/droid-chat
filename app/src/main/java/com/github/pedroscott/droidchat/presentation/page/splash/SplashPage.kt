package com.github.pedroscott.droidchat.presentation.page.splash

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.pedroscott.droidchat.presentation.atomic.template.SplashTemplate
import com.github.pedroscott.droidchat.presentation.navigation.ChatRoute
import com.github.pedroscott.droidchat.presentation.page.common.ObserveActions
import kotlinx.serialization.Serializable

@Serializable
object SplashRoute : ChatRoute<SplashNavAction> {

    @Composable
    override fun Page(handleNavAction: (SplashNavAction) -> Unit) {
        val viewModel: SplashViewModel = hiltViewModel()

        ObserveActions(
            actionFlow = viewModel.action,
            handleAction = handleNavAction
        )

        SplashTemplate()
    }
}