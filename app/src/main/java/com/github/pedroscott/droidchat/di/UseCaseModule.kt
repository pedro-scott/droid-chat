package com.github.pedroscott.droidchat.di

import com.github.pedroscott.droidchat.domain.usecase.signin.SignInUseCase
import com.github.pedroscott.droidchat.domain.usecase.signin.SignInUseCaseImpl
import com.github.pedroscott.droidchat.domain.usecase.validation.ValidateEmailUseCase
import com.github.pedroscott.droidchat.domain.usecase.validation.ValidateEmailUseCaseImpl
import com.github.pedroscott.droidchat.domain.usecase.validation.ValidatePasswordUseCase
import com.github.pedroscott.droidchat.domain.usecase.validation.ValidatePasswordUseCaseImpl
import com.github.pedroscott.droidchat.domain.usecase.validation.ValidationEmptinessUseCase
import com.github.pedroscott.droidchat.domain.usecase.validation.ValidationEmptinessUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    @ViewModelScoped
    fun bindValidateEmptinessUseCase(impl: ValidationEmptinessUseCaseImpl): ValidationEmptinessUseCase

    @Binds
    @ViewModelScoped
    fun bindValidateEmailUseCase(impl: ValidateEmailUseCaseImpl): ValidateEmailUseCase

    @Binds
    @ViewModelScoped
    fun bindValidatePasswordUseCase(impl: ValidatePasswordUseCaseImpl): ValidatePasswordUseCase

    @Binds
    @ViewModelScoped
    fun bindSignInUseCase(impl: SignInUseCaseImpl): SignInUseCase
}