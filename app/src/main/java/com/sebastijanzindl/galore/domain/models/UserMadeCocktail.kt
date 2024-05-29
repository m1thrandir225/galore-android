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

    @SerialName("image")
    val image: String,

    @SerialName("ingredients")
    val ingredients: List<UserMadeCocktailIngredient>,

    @SerialName("instructions")
    val instructions: List<UserMadeCocktailInstruction>,

    @SerialName("user_id")
    val userId: String,

    @SerialName("created_at")
    val createdAt: String,

    @SerialName("embedding")
    val embedding: List<Float>?
)


@Serializable
data class UserMadeCocktailIngredient(
    @SerialName("name")
    val name: String,

    @SerialName("amount")
    val amount: String
)

@Serializable
data class UserMadeCocktailInstruction(
    @SerialName("instruction")
    val instruction: String,

    @SerialName("instruction_image")
    val instructionImage: String
)

