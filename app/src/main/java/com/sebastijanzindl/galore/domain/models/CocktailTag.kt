package com.sebastijanzindl.galore.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CocktailTag(
    @SerialName("tag_id")
    val tagId: String,

    @SerialName("cocktail_id")
    val cocktailId: String
)