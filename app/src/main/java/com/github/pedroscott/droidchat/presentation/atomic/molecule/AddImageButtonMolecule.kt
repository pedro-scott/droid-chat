package com.github.pedroscott.droidchat.presentation.atomic.molecule

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.github.pedroscott.droidchat.R
import com.github.pedroscott.droidchat.presentation.atomic.atom.PaddingAtom
import com.github.pedroscott.droidchat.presentation.theme.ChatDimens
import com.github.pedroscott.droidchat.presentation.theme.DroidChatTheme

@Composable
fun AddImageButtonMolecule(
    image: Any?,
    onAddImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.clickable(onClick = onAddImageClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val imageSize = if (image == null) 60 else 90
        val colorFilter = if (image == null) ColorFilter.tint(MaterialTheme.colorScheme.onSurface) else null
        val borderColor = if (image == null) Color.Transparent else MaterialTheme.colorScheme.onSurface

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(image ?: R.drawable.ic_upload_photo)
                .error(R.drawable.no_profile_image)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .size(imageSize.dp)
                .clip(CircleShape)
                .border(
                    width = 2.dp,
                    color = borderColor,
                    shape = CircleShape
                ),
            contentScale = ContentScale.Crop,
            colorFilter = colorFilter
        )
        if (image == null) {
            PaddingAtom(ChatDimens.Padding.detail)
            Text(
                text = stringResource(id = R.string.common_add_profile_photo),
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    }
}

private class AddImageButtonParameterProvider : CollectionPreviewParameterProvider<String?>(
    listOf(null, "")
)

@Preview(showBackground = true)
@Composable
private fun Preview(
    @PreviewParameter(
        AddImageButtonParameterProvider::class
    ) image: String?
) {
    DroidChatTheme {
        AddImageButtonMolecule(
            image = image,
            onAddImageClick = {}
        )
    }
}