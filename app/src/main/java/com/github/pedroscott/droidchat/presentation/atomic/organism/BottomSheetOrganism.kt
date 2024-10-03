@file:OptIn(ExperimentalMaterial3Api::class)

package com.github.pedroscott.droidchat.presentation.atomic.organism

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import com.github.pedroscott.droidchat.R
import com.github.pedroscott.droidchat.presentation.atomic.molecule.AddImageOptionMolecule
import com.github.pedroscott.droidchat.presentation.theme.DroidChatTheme

@Composable
fun BottomSheetOrganism(
    show: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    containerColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    content: @Composable ColumnScope.() -> Unit
) {
    if (show) {
        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
            modifier = modifier,
            sheetState = sheetState,
            containerColor = containerColor,
            contentColor = contentColor
        ) {
            content()
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

        BottomSheetOrganism(
            show = true,
            onDismissRequest = {},
            sheetState = sheetState
        ) {
            AddImageOptionMolecule(
                iconRes = R.drawable.ic_photo_camera,
                textRes = R.string.common_take_photo,
                onClick = {}
            )
            AddImageOptionMolecule(
                iconRes = R.drawable.ic_photo_library,
                textRes = R.string.common_upload_photo,
                onClick = {}
            )
        }
    }
}