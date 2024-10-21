package com.github.pedroscott.droidchat.domain.usecase.signin

import com.github.pedroscott.droidchat.domain.repository.AuthRepository
import javax.inject.Inject

interface SignInUseCase {
    suspend operator fun invoke(params: Params): Result<Unit>

    data class Params(
        val email: String,
        val password: String,
    )
}

class SignInUseCaseImpl @Inject constructor(
    private val repository: AuthRepository
) : SignInUseCase {

    override suspend fun invoke(params: SignInUseCase.Params): Result<Unit> =
        runCatching { repository.signIn(params.email, params.password) }
}