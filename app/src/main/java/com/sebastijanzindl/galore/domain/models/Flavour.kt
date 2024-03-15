package com.sebastijanzindl.galore.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.sql.Time
import java.util.UUID

@Serializable
data class Flavour(
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("created_at")
    val createdAt: String
)
