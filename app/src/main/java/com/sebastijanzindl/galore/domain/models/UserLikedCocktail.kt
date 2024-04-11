package com.sebastijanzindl.galore.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class UserLikedCocktail(
    @SerialName("cocktail_id")
    val cocktailId: String,

    @SerialName("user_id")
    val userId: String,
)