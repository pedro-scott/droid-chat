package com.github.pedroscott.droidchat.data.datasource

import com.github.pedroscott.droidchat.data.model.request.SignInRequest
import com.github.pedroscott.droidchat.data.model.request.SignUpRequest
import com.github.pedroscott.droidchat.data.model.response.TokenResponse
import com.github.pedroscott.droidchat.data.util.extension.callApi
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import javax.inject.Inject

interface AuthDataSource {
    suspend fun signUp(request: SignUpRequest)
    suspend fun signIn(request: SignInRequest): TokenResponse
}

class AuthDataSourceRemote @Inject constructor(
    private val client: HttpClient
) : AuthDataSource {

    override suspend fun signUp(request: SignUpRequest): Unit =
        client.callApi {
            post("signup") { setBody(request) }
        }

    override suspend fun signIn(request: SignInRequest): TokenResponse =
        client.callApi {
            post("/signin") { setBody(request) }
        }
}