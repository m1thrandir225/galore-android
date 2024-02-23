package com.sebastijanzindl.galore.di

import com.sebastijanzindl.galore.data.repository.AuthenticationRepository
import com.sebastijanzindl.galore.data.repository.impl.AuthenticationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract  class RepositoryModule {

    @Binds
    abstract  fun bindAuthenticationRepository(impl: AuthenticationRepositoryImpl): AuthenticationRepository
}