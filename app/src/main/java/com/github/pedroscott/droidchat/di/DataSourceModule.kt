package com.github.pedroscott.droidchat.di

import com.github.pedroscott.droidchat.data.datasource.AuthDataSource
import com.github.pedroscott.droidchat.data.datasource.AuthDataSourceRemote
import com.github.pedroscott.droidchat.data.datasource.PreferencesDataSource
import com.github.pedroscott.droidchat.data.datasource.PreferencesDataSourceLocal
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
}