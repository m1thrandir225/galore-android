package com.sebastijanzindl.galore.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Notification(
    @SerialName("id")
    val id: String,

    @SerialName("content")
    val content: String,

    @SerialName("user_id")
    val userId: String,

    @SerialName("created_at")
    val createdAt: String
)
