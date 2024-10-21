package com.github.pedroscott.droidchat.data.util.extension

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend inline fun <reified T> HttpClient.callApi(
    crossinline block: suspend HttpClient.() -> HttpResponse
): T = withContext(Dispatchers.IO) {
    this@callApi.block().body()
}