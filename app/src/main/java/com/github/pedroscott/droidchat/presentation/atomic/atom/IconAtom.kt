package com.github.pedroscott.droidchat.presentation.atomic.atom

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.pedroscott.droidchat.R
import com.github.pedroscott.droidchat.presentation.theme.DroidChatTheme

@Composable
fun IconAtom(
    painter: Painter,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    tint: Color? = null
) {
    Icon(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint ?: Color.Unspecified
    )
}

@Preview
@Composable
private fun Preview() {
    DroidChatTheme {
        IconAtom(
            painter = painterResource(id = R.drawable.ic_safety)
        )
    }
}