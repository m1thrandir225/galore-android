package com.sebastijanzindl.galore.di

import com.sebastijanzindl.galore.data.repository.AuthenticationRepository
import com.sebastijanzindl.galore.data.repository.CocktailRepository
import com.sebastijanzindl.galore.data.repository.FlavourRepository
import com.sebastijanzindl.galore.data.repository.UserProfileRepository
import com.sebastijanzindl.galore.data.repository.impl.AuthenticationRepositoryImpl
import com.sebastijanzindl.galore.data.repository.impl.CocktailRepositoryImpl
import com.sebastijanzindl.galore.data.repository.impl.FlavourRepositoryImpl
import com.sebastijanzindl.galore.data.repository.impl.UserProfileRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract  class RepositoryModule {
    @Binds
    abstract fun bindAuthenticationRepository(impl: AuthenticationRepositoryImpl): AuthenticationRepository

    @Binds
    abstract fun bindFlavourRepository(impl: FlavourRepositoryImpl): FlavourRepository

    @Binds
    abstract fun bindCocktailRepository(impl: CocktailRepositoryImpl): CocktailRepository

    @Binds
    abstract fun bindUserProfileRepository(impl: UserProfileRepositoryImpl): UserProfileRepository
}