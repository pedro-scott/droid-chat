package com.github.pedroscott.droidchat.domain.usecase.validation

import com.github.pedroscott.droidchat.domain.entity.validation.DefaultValidationResult
import javax.inject.Inject

interface ValidateEmailUseCase {
    operator fun invoke(email: String): DefaultValidationResult
}

class ValidateEmailUseCaseImpl @Inject constructor() : ValidateEmailUseCase {

    override fun invoke(email: String): DefaultValidationResult {
        if (email.isBlank()) {
            return DefaultValidationResult.Empty
        }

        val regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$".toRegex()
        if (!regex.matches(email)) {
            return DefaultValidationResult.Invalid
        }

        return DefaultValidationResult.Valid
    }
}