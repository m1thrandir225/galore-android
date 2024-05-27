package com.sebastijanzindl.galore.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

@Serializable
data class GenerateCocktailResponse(
    @SerialName("generated_cocktail_id")
    val generatedCocktailId: String
)

interface ApiService {
    @POST("/generate-cocktail")
    fun generateCocktail(
        @Header("Authorization") authorization: String,
        @Body prompt: String
    ): Response<GenerateCocktailResponse>
}