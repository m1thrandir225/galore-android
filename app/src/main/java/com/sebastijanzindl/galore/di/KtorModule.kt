package com.sebastijanzindl.galore.di

import com.sebastijanzindl.galore.data.network.KtorAPIService
import com.sebastijanzindl.galore.data.network.KtorAPIServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object KtorModule {
    @Provides
    fun provideBaseUrl(): String = "https://generate-cocktail-backend.sebastijanzindl.workers.dev/"

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 240000
            }
            install(DefaultRequest) {
                url("https://generate-cocktail-backend.sebastijanzindl.workers.dev/")
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
            install(ContentNegotiation){
                json(Json)
            }
        }
    }
    @Singleton
    @Provides
    fun provideApiService(httpClient: HttpClient): KtorAPIService = KtorAPIServiceImpl(httpClient)
}