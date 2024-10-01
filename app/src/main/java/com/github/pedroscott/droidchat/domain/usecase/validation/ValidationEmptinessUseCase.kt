package com.github.pedroscott.droidchat.domain.usecase.validation

import com.github.pedroscott.droidchat.domain.entity.validation.DefaultValidationResult
import javax.inject.Inject

interface ValidationEmptinessUseCase {
    operator fun invoke(email: String): DefaultValidationResult
}

class ValidationEmptinessUseCaseImpl @Inject constructor() : ValidationEmptinessUseCase {

    override fun invoke(email: String): DefaultValidationResult {
        if (email.isBlank()) {
            return DefaultValidationResult.Empty
        }

        return DefaultValidationResult.Valid
    }
}