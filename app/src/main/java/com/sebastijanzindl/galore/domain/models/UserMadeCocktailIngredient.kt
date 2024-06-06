package com.sebastijanzindl.galore.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserMadeCocktailIngredient(
    @SerialName("name")
    val name: String,

    @SerialName("amount")
    val amount: String
)