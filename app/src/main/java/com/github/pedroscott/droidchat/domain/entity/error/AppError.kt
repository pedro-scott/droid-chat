@file:Suppress("JavaIoSerializableObjectMustHaveReadResolve")

package com.github.pedroscott.droidchat.domain.entity.error

sealed class AppError : Exception() {
    sealed class Common : AppError() {
        data class Unknown(override val message: String?) : Common()
    }
    sealed class Api : AppError() {
        data object Unavailable : Api()
        data object Unauthorized : Api()
        data object BadRequest : Api()
        data object NotFound : Api()
        data object Conflict : Api()
        data object Timeout : Api()
    }
}