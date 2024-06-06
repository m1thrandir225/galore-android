package com.sebastijanzindl.galore.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class  CocktailCardInfo (
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("image")
    val image: String,
)