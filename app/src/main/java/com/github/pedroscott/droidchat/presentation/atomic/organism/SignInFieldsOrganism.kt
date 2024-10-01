package com.github.pedroscott.droidchat.presentation.atomic.organism

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.github.pedroscott.droidchat.R
import com.github.pedroscott.droidchat.presentation.atomic.atom.PaddingAtom
import com.github.pedroscott.droidchat.presentation.atomic.molecule.PrimaryFieldMolecule
import com.github.pedroscott.droidchat.presentation.theme.DroidChatTheme

@Composable
fun SignInFieldsOrganism(
    email: String,
    emailError: String?,
    onEmailChange: (String) -> Unit,
    onEmailFocusChange: (Boolean) -> Unit,
    password: String,
    passwordError: String?,
    onPasswordChange: (String) -> Unit,
    onPasswordFocusChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        PrimaryFieldMolecule(
            value = email,
            onValueChange = onEmailChange,
            modifier = Modifier.fillMaxWidth(),
            onFocusChange = onEmailFocusChange,
            placeholder = stringResource(id = R.string.feature_login_email),
            keyboardType = KeyboardType.Email,
            leadingIcon = R.drawable.ic_envelope,
            errorMessage = emailError
        )
        PaddingAtom()
        PrimaryFieldMolecule(
            value = password,
            onValueChange = onPasswordChange,
            modifier = Modifier.fillMaxWidth(),
            onFocusChange = onPasswordFocusChange,
            placeholder = stringResource(id = R.string.feature_login_password),
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            leadingIcon = R.drawable.ic_lock,
            errorMessage = passwordError
        )
    }
}

@Preview
@Composable
private fun Preview() {
    DroidChatTheme {
        SignInFieldsOrganism(
            email = "",
            onEmailChange = {},
            onEmailFocusChange = {},
            emailError = null,
            password = "",
            onPasswordChange = {},
            onPasswordFocusChange = {},
            passwordError = null
        )
    }
}