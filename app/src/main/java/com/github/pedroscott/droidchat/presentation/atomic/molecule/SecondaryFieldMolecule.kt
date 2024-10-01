package com.github.pedroscott.droidchat.presentation.atomic.molecule

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import com.github.pedroscott.droidchat.presentation.atomic.atom.PaddingAtom
import com.github.pedroscott.droidchat.presentation.atomic.atom.PasswordIconAtom
import com.github.pedroscott.droidchat.presentation.theme.ChatColors
import com.github.pedroscott.droidchat.presentation.theme.DroidChatTheme

@Composable
fun SecondaryFieldMolecule(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onFocusChange: (Boolean) -> Unit = {},
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    infoText: String? = null,
    errorMessage: String? = null
) {
    val showError = errorMessage?.isNotBlank() ?: false
    val showInfo = infoText?.isNotBlank() ?: false

    var isInitialFocus by remember { mutableStateOf(true) }

    val isPasswordField = keyboardType == KeyboardType.Password || keyboardType == KeyboardType.NumberPassword
    var showPassword by remember { mutableStateOf(false) }

    Column(modifier) {
        TextField(
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
            label = {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Bold
                    )
                )
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
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                disabledContainerColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = with(MaterialTheme.colorScheme) { if (showError) error else onSurfaceVariant },
                unfocusedIndicatorColor = if (showError) MaterialTheme.colorScheme.error else Color.Unspecified
            )
        )
        AnimatedVisibility(visible = showError) {
            PaddingAtom()
            Text(
                text = errorMessage.orEmpty(),
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.error
            )
        }
        AnimatedVisibility(visible = showInfo && !showError) {
            PaddingAtom()
            Text(
                text = infoText.orEmpty(),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.labelMedium.copy(
                    color = ChatColors.ColorSuccess,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

private class DefaultSecondaryFieldParameterProvider : CollectionPreviewParameterProvider<String?>(
    listOf(null, "E-mail inválido")
)

@Preview(showBackground = true)
@Composable
private fun DefaultPreview(
    @PreviewParameter(
        DefaultSecondaryFieldParameterProvider::class
    ) errorMessage: String?
) {
    DroidChatTheme {
        SecondaryFieldMolecule(
            value = "",
            onValueChange = {},
            label = "E-mail",
            errorMessage = errorMessage
        )
    }
}

private class PasswordSecondaryFieldParameterProvider : CollectionPreviewParameterProvider<String>(
    listOf("", "12345")
)

@Preview(showBackground = true)
@Composable
private fun PasswordPreview(
    @PreviewParameter(
        PasswordSecondaryFieldParameterProvider::class
    ) value: String
) {
    DroidChatTheme {
        SecondaryFieldMolecule(
            value = value,
            onValueChange = {},
            label = "Senha",
            infoText = "as senhas são iguais",
            keyboardType = KeyboardType.Password,
        )
    }
}