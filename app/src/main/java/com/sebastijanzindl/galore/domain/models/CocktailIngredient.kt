package com.sebastijanzindl.galore.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CocktailIngredient(
    @SerialName("ingredient")
    val ingredient: String,

    @SerialName("amount")
    val amount: String
)