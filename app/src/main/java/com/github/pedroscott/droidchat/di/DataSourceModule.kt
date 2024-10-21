package com.github.pedroscott.droidchat.di

import com.github.pedroscott.droidchat.data.datasource.AuthDataSource
import com.github.pedroscott.droidchat.data.datasource.AuthDataSourceRemote
import com.github.pedroscott.droidchat.data.datasource.PreferencesDataSource
import com.github.pedroscott.droidchat.data.datasource.PreferencesDataSourceLocal
import com.github.pedroscott.droidchat.data.datasource.UserDataSource
import com.github.pedroscott.droidchat.data.datasource.UserDataSourceRemote
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface DataSourceModule {

    @Binds
    @ViewModelScoped
    fun bindAuthDataSource(impl: AuthDataSourceRemote): AuthDataSource

    @Binds
    @ViewModelScoped
    fun bindPreferencesDataSource(impl: PreferencesDataSourceLocal): PreferencesDataSource

    @Binds
    @ViewModelScoped
    fun bindUserDataSource(impl: UserDataSourceRemote): UserDataSource
}