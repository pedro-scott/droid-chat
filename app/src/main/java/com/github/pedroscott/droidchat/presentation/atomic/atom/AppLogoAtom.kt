package com.github.pedroscott.droidchat.presentation.atomic.atom

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.pedroscott.droidchat.R
import com.github.pedroscott.droidchat.presentation.theme.DroidChatTheme

@Composable
fun AppLogoAtom(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = null,
        modifier = modifier
    )
}

@Preview
@Composable
private fun Preview() {
    DroidChatTheme {
        AppLogoAtom()
    }
}