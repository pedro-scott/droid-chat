@file:OptIn(ExperimentalMaterial3Api::class)

package com.github.pedroscott.droidchat.presentation.page.signup

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.pedroscott.droidchat.presentation.atomic.organism.AddImageBottomSheetOrganism
import com.github.pedroscott.droidchat.presentation.atomic.template.SignUpTemplate
import com.github.pedroscott.droidchat.presentation.navigation.ChatRoute
import com.github.pedroscott.droidchat.presentation.page.common.ObserveActions
import com.github.pedroscott.droidchat.presentation.util.extension.asState
import com.github.pedroscott.droidchat.presentation.util.provider.rememberDroidChatFileProvider
import kotlinx.serialization.Serializable

@Serializable
object SignUpRoute : ChatRoute<SignUpAction.Nav> {

    @Composable
    override fun Page(handleNavAction: (SignUpAction.Nav) -> Unit) {
        val viewModel = hiltViewModel<SignUpViewModel>()
        val uiState by viewModel.uiState.asState()
        val context = LocalContext.current
        val fileProvider = rememberDroidChatFileProvider()

        val cameraLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicture(),
            onResult = { captureSucceed ->
                if (captureSucceed) {
                    fileProvider.lastImageUri
                        ?.let(viewModel::setProfileImage)
                }
            }
        )

        val pickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = viewModel::setProfileImage
        )

        ObserveActions(
            actionFlow = viewModel.action,
            handleAction = { action ->
                when (action) {
                    is SignUpAction.Nav -> handleNavAction(action)
                    is SignUpAction.Camera -> {
                        fileProvider
                            .getImageUri(context = context, fileName = "profile_image")
                            ?.let(cameraLauncher::launch)
                    }
                    is SignUpAction.Gallery -> {
                        pickerLauncher.launch(
                            input = PickVisualMediaRequest(
                                mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    }
                }
            }
        )

        AddImageBottomSheetOrganism(
            show = uiState.showAddImageOptions,
            onDismissRequest = viewModel::clearAddImageOptions,
            onItemClick = viewModel::onAddImageOptionSelect
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