package com.github.pedroscott.droidchat.presentation.atomic.atom

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.github.pedroscott.droidchat.presentation.theme.ChatDimens

@Composable
fun ColumnScope.PaddingAtom(height: Dp = ChatDimens.Padding.default) {
    Spacer(modifier = Modifier.height(height))
}

@Composable
fun RowScope.PaddingAtom(width: Dp = ChatDimens.Padding.default) {
    Spacer(modifier = Modifier.width(width))
}