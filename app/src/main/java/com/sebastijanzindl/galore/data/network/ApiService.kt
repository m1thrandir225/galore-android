package com.sebastijanzindl.galore.data.network

import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerateCocktailResponse(
    @SerialName("generated_cocktail_id")
    val generatedCocktailId: String
)

interface ApiService {
    suspend fun generateCocktail(prompt: String, token: String): HttpResponse

}