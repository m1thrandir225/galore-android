package com.sebastijanzindl.galore.di

import com.sebastijanzindl.galore.domain.usecase.SignInGoogleUseCase
import com.sebastijanzindl.galore.domain.usecase.SignUpUseCase
import com.sebastijanzindl.galore.domain.usecase.impl.SignInGoogleUseCaseImpl
import com.sebastijanzindl.galore.domain.usecase.impl.SignUpUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class UseCaseModule {
    @Binds
    abstract fun bindSignUpUseCase(impl: SignUpUseCaseImpl): SignUpUseCase

    @Binds
    abstract fun bindSignInGoogleUseCase(impl: SignInGoogleUseCaseImpl): SignInGoogleUseCase
}