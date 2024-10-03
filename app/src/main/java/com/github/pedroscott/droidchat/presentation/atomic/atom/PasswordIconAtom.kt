package com.github.pedroscott.droidchat.presentation.atomic.atom

import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.pedroscott.droidchat.R
import com.github.pedroscott.droidchat.presentation.theme.DroidChatTheme

@Composable
fun PasswordIconAtom(
    showPassword: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.primary
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        IconAtom(
            painter = painterResource(
                id = if (showPassword)
                    R.drawable.ic_visibility_off
                else R.drawable.ic_visibility
            ),
            tint = tint
        )
    }
}

@Preview
@Composable
private fun Preview() {
    DroidChatTheme {
        var showPassword by remember { mutableStateOf(false) }

        PasswordIconAtom(
            showPassword = showPassword,
            onClick = { showPassword = !showPassword }
        )
    }
}