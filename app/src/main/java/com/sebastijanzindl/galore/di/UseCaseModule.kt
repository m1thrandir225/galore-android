package com.sebastijanzindl.galore.di

import com.sebastijanzindl.galore.domain.usecase.GetAllFlavoursUseCase
import com.sebastijanzindl.galore.domain.usecase.GetUserFlavoursUseCase
import com.sebastijanzindl.galore.domain.usecase.SignInGoogleUseCase
import com.sebastijanzindl.galore.domain.usecase.SignInUseCase
import com.sebastijanzindl.galore.domain.usecase.SignOutUseCase
import com.sebastijanzindl.galore.domain.usecase.SignUpUseCase
import com.sebastijanzindl.galore.domain.usecase.impl.GetAllFlavoursUseCaseImpl
import com.sebastijanzindl.galore.domain.usecase.impl.GetUserFlavoursUseCaseImpl
import com.sebastijanzindl.galore.domain.usecase.impl.SignInGoogleUseCaseImpl
import com.sebastijanzindl.galore.domain.usecase.impl.SignInUseCaseImpl
import com.sebastijanzindl.galore.domain.usecase.impl.SignOutUseCaseImpl
import com.sebastijanzindl.galore.domain.usecase.impl.SignUpUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class UseCaseModule {
    /**
     * Authentication Repository Use Cases
     */
    @Binds
    abstract fun bindSignUpUseCase(impl: SignUpUseCaseImpl): SignUpUseCase
    @Binds
    abstract fun bindSignInGoogleUseCase(impl: SignInGoogleUseCaseImpl): SignInGoogleUseCase
    @Binds
    abstract fun bindSignOutUseCase(impl: SignOutUseCaseImpl): SignOutUseCase
    @Binds
    abstract fun bindSignInUseCase(impl: SignInUseCaseImpl): SignInUseCase
    /**
     * Flavour Repository Use Cases
     */
    @Binds
    abstract fun bindGetUserFlavoursUseCase(impl: GetUserFlavoursUseCaseImpl): GetUserFlavoursUseCase
    @Binds
    abstract fun bindGetAllFlavoursUseCase(impl: GetAllFlavoursUseCaseImpl): GetAllFlavoursUseCase

}