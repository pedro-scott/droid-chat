package com.github.pedroscott.droidchat.data.util.handler

import com.github.pedroscott.droidchat.domain.entity.error.AppError
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import javax.inject.Inject

interface ErrorHandler {
    suspend fun getAppError(e: Throwable): AppError
}

class ErrorHandlerImpl @Inject constructor() : ErrorHandler {

    override suspend fun getAppError(e: Throwable): AppError =
        when (e) {
            is ClientRequestException -> e.getAppError()
            is HttpRequestTimeoutException -> AppError.Api.Timeout
            else -> AppError.Common.Unknown(e.message)
        }

    private suspend fun ClientRequestException.getAppError(): AppError =
        when (response.status) {
            HttpStatusCode.BadRequest -> AppError.Api.BadRequest
            HttpStatusCode.NotFound -> AppError.Api.NotFound
            HttpStatusCode.Conflict -> AppError.Api.Conflict
            HttpStatusCode.Unauthorized -> AppError.Api.Unauthorized
            HttpStatusCode.InternalServerError, HttpStatusCode.ServiceUnavailable -> AppError.Api.Unavailable
            else -> AppError.Common.Unknown(response.bodyAsText())
        }
}