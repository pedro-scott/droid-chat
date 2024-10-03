package com.github.pedroscott.droidchat.presentation.atomic.organism

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.github.pedroscott.droidchat.R
import com.github.pedroscott.droidchat.presentation.atomic.molecule.SecondaryFieldMolecule
import com.github.pedroscott.droidchat.presentation.theme.ChatDimens
import com.github.pedroscott.droidchat.presentation.theme.DroidChatTheme

@Composable
fun SignUpFormOrganism(
    firstName: String,
    firstNameError: String?,
    onFirstNameChange: (String) -> Unit,
    onFirstNameFocusChange: (Boolean) -> Unit,
    lastName: String,
    lastNameError: String?,
    onLastNameChange: (String) -> Unit,
    onLastNameFocusChange: (Boolean) -> Unit,
    email: String,
    emailError: String?,
    onEmailChange: (String) -> Unit,
    onEmailFocusChange: (Boolean) -> Unit,
    password: String,
    passwordInfo: String?,
    passwordError: String?,
    onPasswordChange: (String) -> Unit,
    onPasswordFocusChange: (Boolean) -> Unit,
    confirmation: String,
    confirmationInfo: String?,
    confirmationError: String?,
    onConfirmationChange: (String) -> Unit,
    onConfirmationFocusChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(ChatDimens.Padding.default)
    ) {
        SecondaryFieldMolecule(
            label = stringResource(id = R.string.feature_sign_up_first_name),
            value = firstName,
            onValueChange = onFirstNameChange,
            onFocusChange = onFirstNameFocusChange,
            errorMessage = firstNameError
        )
        SecondaryFieldMolecule(
            label = stringResource(id = R.string.feature_sign_up_last_name),
            value = lastName,
            onValueChange = onLastNameChange,
            onFocusChange = onLastNameFocusChange,
            errorMessage = lastNameError
        )
        SecondaryFieldMolecule(
            label = stringResource(id = R.string.feature_sign_up_email),
            value = email,
            onValueChange = onEmailChange,
            onFocusChange = onEmailFocusChange,
            keyboardType = KeyboardType.Email,
            errorMessage = emailError
        )
        SecondaryFieldMolecule(
            label = stringResource(id = R.string.feature_sign_up_password),
            value = password,
            onValueChange = onPasswordChange,
            onFocusChange = onPasswordFocusChange,
            keyboardType = KeyboardType.Password,
            infoText = passwordInfo,
            errorMessage = passwordError
        )
        SecondaryFieldMolecule(
            label = stringResource(id = R.string.feature_sign_up_password_confirmation),
            value = confirmation,
            onValueChange = onConfirmationChange,
            onFocusChange = onConfirmationFocusChange,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            infoText = confirmationInfo,
            errorMessage = confirmationError
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    DroidChatTheme {
        SignUpFormOrganism(
            firstName = "",
            firstNameError = null,
            onFirstNameChange = {},
            onFirstNameFocusChange = {},
            lastName = "",
            lastNameError = null,
            onLastNameChange = {},
            onLastNameFocusChange = {},
            email = "",
            emailError = null,
            onEmailChange = {},
            onEmailFocusChange = {},
            password = "",
            passwordInfo = null,
            passwordError = null,
            onPasswordChange = {},
            onPasswordFocusChange = {},
            confirmation = "",
            confirmationInfo = null,
            confirmationError = null,
            onConfirmationChange = {},
            onConfirmationFocusChange = {}
        )
    }
}