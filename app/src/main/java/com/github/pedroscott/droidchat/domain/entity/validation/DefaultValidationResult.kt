package com.github.pedroscott.droidchat.domain.entity.validation

sealed interface DefaultValidationResult {
    data object Valid : DefaultValidationResult
    data object Invalid : DefaultValidationResult
    data object Empty : DefaultValidationResult
}