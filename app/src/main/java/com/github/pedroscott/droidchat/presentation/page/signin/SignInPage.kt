package com.github.pedroscott.droidchat.presentation.page.signin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.pedroscott.droidchat.presentation.atomic.template.SignInTemplate
import com.github.pedroscott.droidchat.presentation.navigation.ChatRoute
import com.github.pedroscott.droidchat.presentation.page.common.ObserveActions
import kotlinx.serialization.Serializable

@Serializable
object SignInRoute : ChatRoute<SignInNavAction> {

    @Composable
    override fun Page(handleNavAction: (SignInNavAction) -> Unit) {
        val viewModel: SignInViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        ObserveActions(
            actionFlow = viewModel.action,
            handleAction = handleNavAction
        )

        SignInTemplate(
            email = uiState.email,
            onEmailChange = viewModel::onEmailChange,
            onEmailFocusChange = viewModel::onEmailFocusChange,
            emailError = uiState.emailError?.asString(),
            password = uiState.password,
            onPasswordChange = viewModel::onPasswordChange,
            onPasswordFocusChange = viewModel::onPasswordFocusChange,
            passwordError = uiState.passwordError?.asString(),
            onLinkClick = viewModel::onLinkClick,
            onButtonClick = viewModel::onButtonClick,
            isButtonLoading = uiState.isButtonLoading,
            isButtonEnabled = uiState.isButtonEnabled
        )
    }
}