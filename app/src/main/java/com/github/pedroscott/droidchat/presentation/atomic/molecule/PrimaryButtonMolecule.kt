package com.github.pedroscott.droidchat.presentation.atomic.molecule

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.github.pedroscott.droidchat.presentation.theme.DroidChatTheme

@Composable
fun PrimaryButtonMolecule(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    isEnabled: Boolean = true
) {
    Button(
        onClick = { if (!isLoading) onClick() },
        modifier = modifier.height(64.dp),
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
            disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
        )
    ) {
        Box(modifier = Modifier.animateContentSize()) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(30.dp)
                        .aspectRatio(1f),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Text(
                    text = text,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

private class PrimaryButtonParameterProvider : PreviewParameterProvider<Boolean> {
    override val values = sequenceOf(true, false)
}

@Preview
@Composable
private fun EnabledPreview(
    @PreviewParameter(
        PrimaryButtonParameterProvider::class
    ) isLoading: Boolean
) {
    DroidChatTheme {
        PrimaryButtonMolecule(
            text = "Entrar",
            onClick = {},
            isLoading = isLoading
        )
    }
}

@Preview
@Composable
private fun DisabledPreview(
    @PreviewParameter(
        PrimaryButtonParameterProvider::class
    ) isLoading: Boolean
) {
    DroidChatTheme {
        PrimaryButtonMolecule(
            text = "Entrar",
            onClick = {},
            isLoading = isLoading,
            isEnabled = false
        )
    }
}