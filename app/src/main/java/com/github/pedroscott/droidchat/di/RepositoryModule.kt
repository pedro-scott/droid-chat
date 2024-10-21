package com.github.pedroscott.droidchat.di

import com.github.pedroscott.droidchat.data.repository.AuthRepositoryRemote
import com.github.pedroscott.droidchat.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    @ViewModelScoped
    fun bindAuthRepository(impl: AuthRepositoryRemote): AuthRepository
}