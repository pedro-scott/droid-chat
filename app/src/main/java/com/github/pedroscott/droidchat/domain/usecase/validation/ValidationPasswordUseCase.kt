package com.github.pedroscott.droidchat.domain.usecase.validation

import com.github.pedroscott.droidchat.domain.entity.validation.PasswordValidationResult
import javax.inject.Inject

interface ValidatePasswordUseCase {
    operator fun invoke(password: String): PasswordValidationResult
}

class ValidatePasswordUseCaseImpl @Inject constructor() : ValidatePasswordUseCase {

    override fun invoke(password: String): PasswordValidationResult {
        if (password.isBlank()) {
            return PasswordValidationResult.Empty
        }

        if (password.length < 8) {
            return PasswordValidationResult.InvalidLength
        }

        val letterRegex = "[a-zA-Z]+".toRegex()
        val numberRegex = "[\\d]+".toRegex()
        if (!password.contains(letterRegex) || !password.contains(numberRegex)) {
            return PasswordValidationResult.InvalidFormat
        }

        return PasswordValidationResult.Valid
    }
}