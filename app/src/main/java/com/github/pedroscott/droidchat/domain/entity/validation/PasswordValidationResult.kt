package com.github.pedroscott.droidchat.domain.entity.validation

sealed interface PasswordValidationResult {
    data object Valid : PasswordValidationResult
    data object InvalidFormat : PasswordValidationResult
    data object InvalidLength : PasswordValidationResult
    data object Empty : PasswordValidationResult
}