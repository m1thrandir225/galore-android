package com.sebastijanzindl.galore.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    @SerialName("id")
    val id: String,

    @SerialName("full_name")
    val fullName: String,

    @SerialName("email")
    val email: String,

    @SerialName("avatar_url")
    val avatarUrl: String,

    @SerialName("user_liked_cocktails")
    val likedCocktails: List<Cocktail>?,

    @SerialName("user_liked_flavours")
    val likedFlavours: List<Flavour>?,

    @SerialName("updated_at")
    val updatedAt: String,
)
