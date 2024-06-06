package com.sebastijanzindl.galore.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserMadeCocktail(
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("short_description")
    val shortDescription: String,

    @SerialName("instructions")
    val instructions: List<UserMadeCocktailInstruction>,

    @SerialName("ingredients")
    val ingredients: List<UserMadeCocktailIngredient>,

    @SerialName("image")
    val image: String,

    @SerialName("created_at")
    val createdAt: String,

    @SerialName("user_id")
    val userId: String,

    @SerialName("embedding")
    val embedding: List<Float>?
)