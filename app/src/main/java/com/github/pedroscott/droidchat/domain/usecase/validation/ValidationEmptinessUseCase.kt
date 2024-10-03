package com.github.pedroscott.droidchat.domain.usecase.validation

import com.github.pedroscott.droidchat.domain.entity.validation.DefaultValidationResult
import javax.inject.Inject

interface ValidationEmptinessUseCase {
    operator fun invoke(value: String): DefaultValidationResult
}

class ValidationEmptinessUseCaseImpl @Inject constructor() : ValidationEmptinessUseCase {

    override fun invoke(value: String): DefaultValidationResult {
        if (value.isBlank()) {
            return DefaultValidationResult.Empty
        }

        return DefaultValidationResult.Valid
    }
}