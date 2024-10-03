package com.github.pedroscott.droidchat.presentation.atomic.template

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.pedroscott.droidchat.R
import com.github.pedroscott.droidchat.presentation.atomic.atom.AppLogoAtom
import com.github.pedroscott.droidchat.presentation.atomic.atom.PaddingAtom
import com.github.pedroscott.droidchat.presentation.atomic.molecule.AddImageButtonMolecule
import com.github.pedroscott.droidchat.presentation.atomic.organism.ButtonWithLinkOrganism
import com.github.pedroscott.droidchat.presentation.atomic.organism.SignUpFormOrganism
import com.github.pedroscott.droidchat.presentation.theme.ChatColors
import com.github.pedroscott.droidchat.presentation.theme.ChatDimens
import com.github.pedroscott.droidchat.presentation.theme.DroidChatTheme

@Composable
fun SignUpTemplate(
    image: Uri?,
    onAddImageClick: () -> Unit,
    firstName: String,
    firstNameError: String?,
    onFirstNameChange: (String) -> Unit,
    onFirstNameFocusChange: (Boolean, String) -> Unit,
    lastName: String,
    lastNameError: String?,
    onLastNameChange: (String) -> Unit,
    onLastNameFocusChange: (Boolean, String) -> Unit,
    email: String,
    emailError: String?,
    onEmailChange: (String) -> Unit,
    onEmailFocusChange: (Boolean, String) -> Unit,
    password: String,
    passwordInfo: String?,
    passwordError: String?,
    onPasswordChange: (String) -> Unit,
    onPasswordFocusChange: (Boolean, String) -> Unit,
    confirmation: String,
    confirmationInfo: String?,
    confirmationError: String?,
    onConfirmationChange: (String) -> Unit,
    onConfirmationFocusChange: (Boolean, String) -> Unit,
    onLinkClick: () -> Unit,
    onButtonClick: () -> Unit,
    isButtonLoading: Boolean,
    isButtonEnabled: Boolean
) {
    Box(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ChatColors.BackgroundGradient),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PaddingAtom(56.dp)
            AppLogoAtom()
            PaddingAtom()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = MaterialTheme.shapes.extraLarge.copy(
                            bottomStart = CornerSize(0.dp),
                            bottomEnd = CornerSize(0.dp)
                        )
                    )
            ) {
                Column(
                    modifier = Modifier.padding(ChatDimens.Padding.default),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AddImageButtonMolecule(
                        image = image,
                        onAddImageClick = onAddImageClick
                    )
                    PaddingAtom()
                    SignUpFormOrganism(
                        firstName = firstName,
                        firstNameError = firstNameError,
                        onFirstNameChange = onFirstNameChange,
                        onFirstNameFocusChange = onFirstNameFocusChange,
                        lastName = lastName,
                        lastNameError = lastNameError,
                        onLastNameChange = onLastNameChange,
                        onLastNameFocusChange = onLastNameFocusChange,
                        email = email,
                        emailError = emailError,
                        onEmailChange = onEmailChange,
                        onEmailFocusChange = onEmailFocusChange,
                        password = password,
                        passwordInfo = passwordInfo,
                        passwordError = passwordError,
                        onPasswordChange = onPasswordChange,
                        onPasswordFocusChange = onPasswordFocusChange,
                        confirmation = confirmation,
                        confirmationInfo = confirmationInfo,
                        confirmationError = confirmationError,
                        onConfirmationChange = onConfirmationChange,
                        onConfirmationFocusChange = onConfirmationFocusChange
                    )
                    PaddingAtom(36.dp)
                    ButtonWithLinkOrganism(
                        buttonText = stringResource(id = R.string.feature_sign_up_button),
                        onButtonClick = onButtonClick,
                        isButtonLoading = isButtonLoading,
                        isButtonEnabled = isButtonEnabled,
                        linkText = stringResource(id = R.string.feature_sign_up_has_account),
                        onLinkClick = onLinkClick
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    DroidChatTheme {
        SignUpTemplate(
            image = null,
            onAddImageClick = {},
            firstName = "",
            firstNameError = null,
            onFirstNameChange = {},
            onFirstNameFocusChange = { _, _ -> },
            lastName = "",
            lastNameError = null,
            onLastNameChange = {},
            onLastNameFocusChange = { _, _ -> },
            email = "",
            emailError = null,
            onEmailChange = {},
            onEmailFocusChange = { _, _ -> },
            password = "",
            passwordInfo = null,
            passwordError = null,
            onPasswordChange = {},
            onPasswordFocusChange = { _, _ -> },
            confirmation = "",
            confirmationInfo = null,
            confirmationError = null,
            onConfirmationChange = {},
            onConfirmationFocusChange = { _, _ -> },
            onLinkClick = {},
            onButtonClick = {},
            isButtonLoading = false,
            isButtonEnabled = true
        )
    }
}