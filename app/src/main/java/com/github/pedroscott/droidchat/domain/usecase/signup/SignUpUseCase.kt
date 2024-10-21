package com.github.pedroscott.droidchat.domain.usecase.signup

import com.github.pedroscott.droidchat.domain.repository.AuthRepository
import javax.inject.Inject

interface SignUpUseCase {
    suspend operator fun invoke(params: Params): Result<Unit>

    data class Params(
        val email: String,
        val password: String,
        val firstName: String,
        val lastName: String,
        val profilePictureId: String?
    )
}

class SignUpUseCaseImpl @Inject constructor(
    private val repository: AuthRepository
) : SignUpUseCase {

    override suspend fun invoke(params: SignUpUseCase.Params): Result<Unit> =
        runCatching {
            with(params) {
                repository.signUp(
                    username = email,
                    password = password,
                    firstName = firstName,
                    lastName = lastName,
                    profilePictureId = profilePictureId
                )
            }
        }
}