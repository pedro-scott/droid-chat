package com.github.pedroscott.droidchat.data.repository

import com.github.pedroscott.droidchat.data.datasource.UserDataSource
import com.github.pedroscott.droidchat.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryRemote @Inject constructor(
    private val dataSource: UserDataSource
) : UserRepository {

    override suspend fun uploadProfileImage(imagePath: String): String =
        dataSource.uploadProfileImage(imagePath).id
}