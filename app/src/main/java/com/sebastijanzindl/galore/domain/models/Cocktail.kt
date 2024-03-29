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

    @SerialName("steps")
    val steps: String,

    @SerialName("ingredients")
    val ingredients: String,

    @SerialName("tags")
    val tags: List<Tag>?,

    val flavours: List<Flavour>?,

    @SerialName("created_at")
    val createdAt: String,
)