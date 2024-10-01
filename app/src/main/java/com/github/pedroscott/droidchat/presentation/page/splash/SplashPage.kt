package com.github.pedroscott.droidchat.presentation.page.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.pedroscott.droidchat.presentation.atomic.template.SplashTemplate
import com.github.pedroscott.droidchat.presentation.navigation.ChatRoute
import kotlinx.serialization.Serializable

@Serializable
object SplashRoute : ChatRoute<SplashNavAction> {

    @Composable
    override fun Page(handleNavAction: (SplashNavAction) -> Unit) {
        val viewModel: SplashViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        LaunchedEffect(uiState.navAction) {
            uiState.navAction?.let {
                handleNavAction(it)
                viewModel.clearNavAction()
            }
        }

        SplashTemplate()
    }
}