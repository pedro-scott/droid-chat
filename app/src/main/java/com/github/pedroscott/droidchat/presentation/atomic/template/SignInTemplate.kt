package com.github.pedroscott.droidchat.presentation.atomic.template

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
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
import com.github.pedroscott.droidchat.presentation.atomic.organism.ButtonWithLinkOrganism
import com.github.pedroscott.droidchat.presentation.atomic.organism.SignInFormOrganism
import com.github.pedroscott.droidchat.presentation.theme.ChatColors
import com.github.pedroscott.droidchat.presentation.theme.ChatDimens
import com.github.pedroscott.droidchat.presentation.theme.DroidChatTheme

@Composable
fun SignInTemplate(
    email: String,
    emailError: String?,
    onEmailChange: (String) -> Unit,
    onEmailFocusChange: (Boolean, String) -> Unit,
    password: String,
    passwordError: String?,
    onPasswordChange: (String) -> Unit,
    onPasswordFocusChange: (Boolean, String) -> Unit,
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
        SignInFormOrganism(
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
        ButtonWithLinkOrganism(
            buttonText = stringResource(id = R.string.feature_login_button),
            onButtonClick = {
                focusManager.clearFocus()
                onButtonClick()
            },
            isButtonLoading = isButtonLoading,
            isButtonEnabled = isButtonEnabled,
            linkText = stringResource(id = R.string.feature_login_no_account),
            onLinkClick = onLinkClick,
            linkColor = MaterialTheme.colorScheme.onPrimary,
            spacer = 57.dp
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
            onEmailFocusChange = { _, _ -> },
            emailError = null,
            password = "",
            onPasswordChange = {},
            onPasswordFocusChange = { _, _ -> },
            passwordError = null,
            onLinkClick = {},
            onButtonClick = {},
            isButtonLoading = false,
            isButtonEnabled = true
        )
    }
}