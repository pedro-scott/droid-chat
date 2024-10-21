package com.github.pedroscott.droidchat.domain.usecase.signup

import com.github.pedroscott.droidchat.domain.repository.UserRepository
import javax.inject.Inject

interface UploadProfileImageUseCase {
    suspend operator fun invoke(filePath: String): Result<String>
}

class UploadProfileImageUseCaseImpl @Inject constructor(
    private val repository: UserRepository
) : UploadProfileImageUseCase {

    override suspend fun invoke(filePath: String): Result<String> =
        runCatching { repository.uploadProfileImage(filePath) }
}