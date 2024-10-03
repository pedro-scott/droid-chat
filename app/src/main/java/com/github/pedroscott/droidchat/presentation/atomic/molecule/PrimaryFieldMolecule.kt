package com.github.pedroscott.droidchat.presentation.atomic.molecule

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import com.github.pedroscott.droidchat.R
import com.github.pedroscott.droidchat.presentation.atomic.atom.IconAtom
import com.github.pedroscott.droidchat.presentation.atomic.atom.PaddingAtom
import com.github.pedroscott.droidchat.presentation.atomic.atom.PasswordIconAtom
import com.github.pedroscott.droidchat.presentation.theme.ChatDimens
import com.github.pedroscott.droidchat.presentation.theme.DroidChatTheme

@Composable
fun PrimaryFieldMolecule(
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onFocusChange: (Boolean) -> Unit = {},
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    @DrawableRes leadingIcon: Int? = null,
    errorMessage: String? = null
) {
    val showError = errorMessage?.isNotBlank() ?: false

    var isInitialFocus by remember { mutableStateOf(true) }

    val isPasswordField = keyboardType == KeyboardType.Password || keyboardType == KeyboardType.NumberPassword
    var showPassword by remember { mutableStateOf(false) }

    Column(modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    if (!isInitialFocus) {
                        onFocusChange(it.isFocused)
                    } else {
                        isInitialFocus = false
                    }
                },
            placeholder = { Text(text = placeholder) },
            leadingIcon = {
                leadingIcon?.let {
                    IconAtom(
                        painter = painterResource(id = it),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            },
            trailingIcon = {
                if (isPasswordField && value.isNotBlank()) {
                    PasswordIconAtom(
                        showPassword = showPassword,
                        onClick = { showPassword = !showPassword }
                    )
                }
            },
            visualTransformation = if (isPasswordField && !showPassword)
                PasswordVisualTransformation()
            else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            singleLine = true,
            shape = CircleShape,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                disabledContainerColor = MaterialTheme.colorScheme.surface,
                focusedBorderColor = with(MaterialTheme.colorScheme) { if (showError) error else primary },
                unfocusedBorderColor = with(MaterialTheme.colorScheme) { if (showError) error else onSurfaceVariant }
            )
        )
        AnimatedVisibility(visible = showError) {
            PaddingAtom()
            Text(
                text = errorMessage.orEmpty(),
                modifier = Modifier.padding(start = ChatDimens.Padding.medium),
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

private class DefaultPrimaryFieldParameterProvider : CollectionPreviewParameterProvider<String?>(
    listOf(null, "E-mail inválido")
)

@Preview
@Composable
private fun DefaultPreview(
    @PreviewParameter(
        DefaultPrimaryFieldParameterProvider::class
    ) errorMessage: String?
) {
    DroidChatTheme {
        PrimaryFieldMolecule(
            placeholder = "E-mail",
            value = "",
            onValueChange = {},
            leadingIcon = R.drawable.ic_envelope,
            errorMessage = errorMessage
        )
    }
}

private class PasswordPrimaryFieldParameterProvider : CollectionPreviewParameterProvider<String>(
    listOf("", "12345")
)

@Preview
@Composable
private fun PasswordPreview(
    @PreviewParameter(
        PasswordPrimaryFieldParameterProvider::class
    ) value: String
) {
    DroidChatTheme {
        PrimaryFieldMolecule(
            placeholder = "Senha",
            value = value,
            onValueChange = {},
            leadingIcon = R.drawable.ic_lock,
            keyboardType = KeyboardType.Password
        )
    }
}