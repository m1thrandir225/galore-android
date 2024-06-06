package com.sebastijanzindl.galore.data.network

import io.ktor.client.statement.HttpResponse

interface KtorAPIService {
    suspend fun generateCocktail(prompt: String, token: String): HttpResponse

    suspend fun getDailyHome(token: String): HttpResponse

}