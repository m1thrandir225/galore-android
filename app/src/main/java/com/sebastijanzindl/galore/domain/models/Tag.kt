package com.sebastijanzindl.galore.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tag(
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("created_at")
    val createdAt: String,

    @SerialName("embedding")
    val embeddingVector:  List<Float>
)
