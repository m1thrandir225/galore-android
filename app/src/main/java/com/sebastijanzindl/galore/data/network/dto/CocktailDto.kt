package com.sebastijanzindl.galore.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CocktailDto(
    @SerialName("strDrink")
    val name: String,

    @SerialName("id")
    val id: String,

    @SerialName("strDrinkThumb")
    val thumbnail: String
)
