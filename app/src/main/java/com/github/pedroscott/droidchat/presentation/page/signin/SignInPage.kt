package com.github.pedroscott.droidchat.presentation.page.signin

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.pedroscott.droidchat.R
import com.github.pedroscott.droidchat.presentation.atomic.template.SignInTemplate
import com.github.pedroscott.droidchat.presentation.navigation.ChatRoute
import com.github.pedroscott.droidchat.presentation.page.common.ObserveActions
import com.github.pedroscott.droidchat.presentation.util.extension.asState
import kotlinx.serialization.Serializable

@Serializable
object SignInRoute : ChatRoute<SignInNavAction> {

    @Composable
    override fun Page(handleNavAction: (SignInNavAction) -> Unit) {
        val viewModel: SignInViewModel = hiltViewModel()
        val uiState by viewModel.uiState.asState()

        ObserveActions(
            actionFlow = viewModel.action,
            handleAction = { handleNavAction(it) }
        )

        uiState.errorMessage?.let { message ->
            AlertDialog(
                onDismissRequest = viewModel::clearError,
                confirmButton = {
                    TextButton(onClick = viewModel::clearError) {
                        Text(text = stringResource(id = R.string.common_ok))
                    }
                },
                title = { Text(text = stringResource(id = R.string.common_generic_error_title)) },
                text = { Text(text = message.asString()) }
            )
        }

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