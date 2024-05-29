package com.sebastijanzindl.galore.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Cocktail(
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("image")
    val image: String,

    @SerialName("instructions")
    val instructions: List<String>,

    @SerialName("ingredients")
    val ingredients: List<CocktailIngredient>,

    @SerialName("glass")
    val glass: String = ""

);

@Serializable
data class CocktailIngredient(
    @SerialName("ingredient")
    val ingredient: String,

    @SerialName("amount")
    val amount: String
)


@Serializable
data class UserLikedCocktailsResponse(
    @SerialName("cocktails")
    val cocktails: List<Cocktail>
)

@Serializable
data class GetSingleCocktailResponse(
    @SerialName("cocktail")
    val cocktail: Cocktail
)

