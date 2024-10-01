package com.github.pedroscott.droidchat.presentation.atomic.template

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.pedroscott.droidchat.R
import com.github.pedroscott.droidchat.presentation.atomic.atom.AppLogoAtom
import com.github.pedroscott.droidchat.presentation.atomic.atom.PaddingAtom
import com.github.pedroscott.droidchat.presentation.atomic.molecule.PrimaryButtonMolecule
import com.github.pedroscott.droidchat.presentation.atomic.organism.SignInFieldsOrganism
import com.github.pedroscott.droidchat.presentation.theme.ChatColors
import com.github.pedroscott.droidchat.presentation.theme.ChatDimens
import com.github.pedroscott.droidchat.presentation.theme.DroidChatTheme
import com.github.pedroscott.droidchat.util.extension.parseHtml

@Composable
fun SignInTemplate(
    email: String,
    emailError: String?,
    onEmailChange: (String) -> Unit,
    onEmailFocusChange: (Boolean) -> Unit,
    password: String,
    passwordError: String?,
    onPasswordChange: (String) -> Unit,
    onPasswordFocusChange: (Boolean) -> Unit,
    onLinkClick: () -> Unit,
    onButtonClick: () -> Unit,
    isButtonLoading: Boolean,
    isButtonEnabled: Boolean
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(ChatColors.BackgroundGradient)
            .padding(horizontal = ChatDimens.Padding.default),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppLogoAtom()
        PaddingAtom(78.dp)
        SignInFieldsOrganism(
            email = email,
            onEmailChange = onEmailChange,
            onEmailFocusChange = onEmailFocusChange,
            emailError = emailError,
            password = password,
            onPasswordChange = onPasswordChange,
            onPasswordFocusChange = onPasswordFocusChange,
            passwordError = passwordError
        )
        PaddingAtom(97.dp)
        PrimaryButtonMolecule(
            text = stringResource(id = R.string.feature_login_button),
            onClick = {
                focusManager.clearFocus()
                onButtonClick()
            },
            isLoading = isButtonLoading,
            isEnabled = isButtonEnabled
        )
        PaddingAtom(57.dp)
        Text(
            text = stringResource(id = R.string.feature_login_no_account).parseHtml(),
            modifier = Modifier.clickable(onClick = onLinkClick),
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview
@Composable
private fun Preview() {
    DroidChatTheme {
        SignInTemplate(
            email = "",
            onEmailChange = {},
            onEmailFocusChange = {},
            emailError = null,
            password = "",
            onPasswordChange = {},
            onPasswordFocusChange = {},
            passwordError = null,
            onLinkClick = {},
            onButtonClick = {},
            isButtonLoading = false,
            isButtonEnabled = true
        )
    }
}