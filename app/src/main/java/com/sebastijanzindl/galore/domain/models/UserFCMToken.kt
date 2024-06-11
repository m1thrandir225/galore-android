package com.sebastijanzindl.galore.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserFCMTokenRequest(
    @SerialName("user_id")
    val userId: String,

    @SerialName("token")
    val fcmToken: String,

    @SerialName("device_id")
    val deviceID: String,
)

@Serializable
data class UserFCMToken(
    @SerialName("id")
    val id: String,

    @SerialName("token")
    val fcmToken: String,

    @SerialName("user_id")
    val userId: String,

    @SerialName("device_id")
    val deviceID: String,

    @SerialName("created_at")
    val createdAt: String
)
