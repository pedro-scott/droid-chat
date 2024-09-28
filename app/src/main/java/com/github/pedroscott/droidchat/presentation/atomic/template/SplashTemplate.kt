package com.github.pedroscott.droidchat.presentation.atomic.template

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.pedroscott.droidchat.R
import com.github.pedroscott.droidchat.presentation.atomic.atom.AppLogoAtom
import com.github.pedroscott.droidchat.presentation.atomic.atom.PaddingAtom
import com.github.pedroscott.droidchat.presentation.atomic.molecule.IconAndTextMolecule
import com.github.pedroscott.droidchat.presentation.theme.ChatColors
import com.github.pedroscott.droidchat.presentation.theme.ChatDimens
import com.github.pedroscott.droidchat.presentation.theme.DroidChatTheme

@Composable
fun SplashTemplate() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = ChatColors.BackgroundGradient)
            .padding(ChatDimens.Padding.default),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppLogoAtom()
        PaddingAtom(height = 77.dp)
        IconAndTextMolecule(
            iconRes = R.drawable.ic_safety,
            textRes = R.string.splash_safety_info
        )
    }
}

@Preview
@Composable
private fun Preview() {
    DroidChatTheme {
        SplashTemplate()
    }
}