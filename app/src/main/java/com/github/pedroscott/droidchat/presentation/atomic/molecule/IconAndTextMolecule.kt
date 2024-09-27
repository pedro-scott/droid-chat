package com.github.pedroscott.droidchat.presentation.atomic.molecule

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.github.pedroscott.droidchat.R
import com.github.pedroscott.droidchat.presentation.atomic.atom.IconAtom
import com.github.pedroscott.droidchat.presentation.atomic.atom.PaddingAtom
import com.github.pedroscott.droidchat.presentation.theme.DroidChatTheme

@Composable
fun IconAndTextMolecule(
    @DrawableRes iconRes: Int,
    @StringRes textRes: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconAtom(painter = painterResource(id = iconRes))
        PaddingAtom()
        Text(
            text = stringResource(id = textRes),
            color = Color.White,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview
@Composable
private fun Preview() {
    DroidChatTheme {
        IconAndTextMolecule(
            iconRes = R.drawable.ic_safety,
            textRes = R.string.splash_safety_info
        )
    }
}