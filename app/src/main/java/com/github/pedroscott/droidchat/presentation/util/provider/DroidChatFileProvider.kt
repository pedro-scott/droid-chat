package com.github.pedroscott.droidchat.presentation.util.provider

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.core.content.FileProvider
import com.github.pedroscott.droidchat.R
import java.io.File

class DroidChatFileProvider : FileProvider(R.xml.file_paths) {

    var lastImageUri: Uri? = null

    fun getImageUri(
        context: Context,
        fileName: String
    ): Uri? {
        val tempFile = File.createTempFile(fileName, ".jpg", context.cacheDir)
        val authority = context.packageName + ".fileprovider"

        return getUriForFile(context, authority, tempFile).also { lastImageUri = it }
    }
}

@Composable
fun rememberDroidChatFileProvider() = remember { DroidChatFileProvider() }