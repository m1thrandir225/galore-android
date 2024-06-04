package com.sebastijanzindl.galore.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerateCocktailResponse(
    @SerialName("generated_cocktail_id")
    val generatedCocktailId: String
)