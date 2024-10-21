package com.github.pedroscott.droidchat.presentation.util.provider

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.core.content.FileProvider
import com.github.pedroscott.droidchat.R
import okio.buffer
import okio.sink
import okio.source
import java.io.File

class DroidChatFileProvider : FileProvider(R.xml.file_paths) {

    var lastImageUri: Uri? = null
    var lastFilePath: String? = null

    fun getImageUri(
        context: Context,
        fileName: String
    ): Uri? {
        val tempFile = File.createTempFile(fileName, ".jpg", context.cacheDir)
        val authority = context.packageName + ".fileprovider"

        return getUriForFile(context, authority, tempFile).also {
            lastImageUri = it
            lastFilePath = tempFile.path
        }
    }

    fun createTempFile(context: Context, fileName: String, uri: Uri): File {
        val tempFile = File.createTempFile(fileName, ".jpg", context.cacheDir)

        context.contentResolver.openInputStream(uri)?.use { inputStream ->
            val source = inputStream.source().buffer()
            val sink = tempFile.sink().buffer()
            source.use { input ->
                sink.use { output ->
                    output.writeAll(input)
                }
            }
        }

        return tempFile.also { lastFilePath = it.path }
    }
}

@Composable
fun rememberDroidChatFileProvider() = remember { DroidChatFileProvider() }