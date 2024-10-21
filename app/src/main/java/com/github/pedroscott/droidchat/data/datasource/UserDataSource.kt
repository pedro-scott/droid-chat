package com.github.pedroscott.droidchat.data.datasource

import com.github.pedroscott.droidchat.data.model.response.ImageResponse
import com.github.pedroscott.droidchat.data.util.extension.callApi
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import java.io.File
import javax.inject.Inject

interface UserDataSource {
    suspend fun uploadProfileImage(imagePath: String): ImageResponse
}

class UserDataSourceRemote @Inject constructor(
    private val client: HttpClient
) : UserDataSource {

    override suspend fun uploadProfileImage(imagePath: String): ImageResponse =
        client.callApi {
            val file = File(imagePath)

            submitFormWithBinaryData(
                url = "profile-picture",
                formData = formData {
                    append(
                        key = "profilePicture",
                        value = file.readBytes(),
                        headers = Headers.build {
                            append(name = HttpHeaders.ContentType, value = "image/${file.extension}")
                            append(name = HttpHeaders.ContentDisposition, value = "filename=${file.name}")
                        }
                    )
                }
            )
        }
}