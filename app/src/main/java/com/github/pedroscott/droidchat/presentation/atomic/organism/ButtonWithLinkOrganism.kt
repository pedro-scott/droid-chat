package com.github.pedroscott.droidchat.presentation.atomic.organism

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.github.pedroscott.droidchat.presentation.atomic.atom.PaddingAtom
import com.github.pedroscott.droidchat.presentation.atomic.molecule.PrimaryButtonMolecule
import com.github.pedroscott.droidchat.presentation.theme.ChatDimens
import com.github.pedroscott.droidchat.presentation.theme.DroidChatTheme
import com.github.pedroscott.droidchat.presentation.util.extension.parseHtml

@Composable
fun ButtonWithLinkOrganism(
    buttonText: String,
    isButtonLoading: Boolean,
    isButtonEnabled: Boolean,
    onButtonClick: () -> Unit,
    linkText: String,
    onLinkClick: () -> Unit,
    modifier: Modifier = Modifier,
    linkColor: Color = MaterialTheme.colorScheme.onSurface,
    spacer: Dp = ChatDimens.Padding.default
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PrimaryButtonMolecule(
            text = buttonText,
            onClick = onButtonClick,
            isLoading = isButtonLoading,
            isEnabled = isButtonEnabled
        )
        PaddingAtom(spacer)
        Text(
            text = linkText.parseHtml(),
            modifier = Modifier.clickable(onClick = onLinkClick),
            color = linkColor
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    DroidChatTheme {
        ButtonWithLinkOrganism(
            buttonText = "Cadastrar",
            isButtonLoading = false,
            isButtonEnabled = true,
            onButtonClick = {},
            linkText = "Já possui uma conta? <font color=#00BCCE><u>Fazer login</u></font>",
            onLinkClick = {}
        )
    }
}