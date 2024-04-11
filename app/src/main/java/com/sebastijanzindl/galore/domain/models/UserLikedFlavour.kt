package com.sebastijanzindl.galore.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class UserLikedFlavour(
    @SerialName("flavour_id")
    val flavourId: String,

    @SerialName("user_id")
    val  userId: String
)