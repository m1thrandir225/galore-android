package com.sebastijanzindl.galore.domain.models

import kotlinx.serialization.Serializable
import java.sql.Time
import java.util.UUID

@Serializable
data class Flavour(
    val id: String,
    val name: String,
    val created_at: String
)
