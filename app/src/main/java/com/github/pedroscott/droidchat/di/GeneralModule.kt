package com.github.pedroscott.droidchat.di

import com.github.pedroscott.droidchat.data.util.handler.ErrorHandler
import com.github.pedroscott.droidchat.data.util.handler.ErrorHandlerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class GeneralModule {

    @Binds
    @Singleton
    abstract fun bindAppErrorHandler(impl: ErrorHandlerImpl): ErrorHandler
}