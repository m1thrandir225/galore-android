package com.sebastijanzindl.galore.data.network.response

import com.sebastijanzindl.galore.domain.models.Flavour
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserFlavourResponse(
    @SerialName("user_id")
    val userId: String,

    @SerialName("flavours")
    val flavour: Flavour
)