package com.sebastijanzindl.galore.di

import com.sebastijanzindl.galore.data.repository.PermissionsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePermissionsRepository() : PermissionsRepository {
        return PermissionsRepository()
    }
}