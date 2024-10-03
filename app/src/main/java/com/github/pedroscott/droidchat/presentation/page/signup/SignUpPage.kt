@file:OptIn(ExperimentalMaterial3Api::class)

package com.github.pedroscott.droidchat.presentation.page.signup

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.pedroscott.droidchat.presentation.atomic.organism.AddImageBottomSheetOrganism
import com.github.pedroscott.droidchat.presentation.atomic.template.SignUpTemplate
import com.github.pedroscott.droidchat.presentation.navigation.ChatRoute
import kotlinx.serialization.Serializable

@Serializable
object SignUpRoute : ChatRoute<SignUpNavAction> {

    @Composable
    override fun Page(handleNavAction: (SignUpNavAction) -> Unit) {
        val viewModel = hiltViewModel<SignUpViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        LaunchedEffect(uiState.navAction) {
            uiState.navAction?.let {
                handleNavAction(it)
                viewModel.clearNavAction()
            }
        }

        AddImageBottomSheetOrganism(
            show = uiState.showUploadImageOptions,
            onDismissRequest = viewModel::clearUploadImageOptions,
            onItemClick = viewModel::onUploadImageOptionSelect
        )

        SignUpTemplate(
            image = uiState.profileImage,
            onAddImageClick = viewModel::onAddImageClick,
            firstName = uiState.firstName,
            firstNameError = uiState.firstNameError?.asString(),
            onFirstNameChange = viewModel::onFirstNameChange,
            onFirstNameFocusChange = viewModel::onFirstNameFocusChange,
            lastName = uiState.lastName,
            lastNameError = uiState.lastNameError?.asString(),
            onLastNameChange = viewModel::onLastNameChange,
            onLastNameFocusChange = viewModel::onLastNameFocusChange,
            email = uiState.email,
            emailError = uiState.emailError?.asString(),
            onEmailChange = viewModel::onEmailChange,
            onEmailFocusChange = viewModel::onEmailFocusChange,
            password = uiState.password,
            passwordInfo = uiState.passwordInfo?.asString(),
            passwordError = uiState.passwordError?.asString(),
            onPasswordChange = viewModel::onPasswordChange,
            onPasswordFocusChange = viewModel::onPasswordFocusChange,
            confirmation = uiState.confirmation,
            confirmationInfo = uiState.confirmationInfo?.asString(),
            confirmationError = uiState.confirmationError?.asString(),
            onConfirmationChange = viewModel::onConfirmationChange,
            onConfirmationFocusChange = viewModel::onConfirmationFocusChange,
            onLinkClick = viewModel::onLinkClick,
            onButtonClick = viewModel::onButtonClick,
            isButtonLoading = uiState.isButtonLoading,
            isButtonEnabled = uiState.isButtonEnabled
        )
    }
}