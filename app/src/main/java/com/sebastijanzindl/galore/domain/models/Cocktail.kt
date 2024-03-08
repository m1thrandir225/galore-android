package com.sebastijanzindl.galore.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Cocktail(
    val id: String,
    val name: String,
    val createdAt: String
)
