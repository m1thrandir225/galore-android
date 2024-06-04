package com.sebastijanzindl.galore.data.repository

import io.ktor.client.statement.HttpResponse

interface GenerateCocktailRepository {
    suspend fun generateCocktail(prompt: String, token: String): HttpResponse

}