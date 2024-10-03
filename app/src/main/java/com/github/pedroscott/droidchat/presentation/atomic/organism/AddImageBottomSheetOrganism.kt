@file:OptIn(ExperimentalMaterial3Api::class)

package com.github.pedroscott.droidchat.presentation.atomic.organism

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import com.github.pedroscott.droidchat.presentation.atomic.molecule.AddImageOptionMolecule
import com.github.pedroscott.droidchat.presentation.model.AddImageOption
import com.github.pedroscott.droidchat.presentation.theme.DroidChatTheme

@Composable
fun AddImageBottomSheetOrganism(
    show: Boolean,
    onDismissRequest: () -> Unit,
    onItemClick: (AddImageOption) -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    options: List<AddImageOption> = AddImageOption.entries
) {
    BottomSheetOrganism(
        show = show,
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        sheetState = sheetState
    ) {
        options.forEach { option ->
            AddImageOptionMolecule(
                iconRes = option.iconRes,
                textRes = option.textRes,
                onClick = { onItemClick(option) }
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    DroidChatTheme {
        val context = LocalContext.current
        val sheetState by remember {
            mutableStateOf(
                SheetState(
                    skipPartiallyExpanded = false,
                    density = Density(context),
                    initialValue = SheetValue.Expanded
                )
            )
        }

        AddImageBottomSheetOrganism(
            show = true,
            onDismissRequest = {},
            onItemClick = {},
            sheetState = sheetState
        )
    }
}