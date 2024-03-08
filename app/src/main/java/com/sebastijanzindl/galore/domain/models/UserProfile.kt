package com.sebastijanzindl.galore.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    val firstName: String,
    val lastName: String,
    val email: String,
    val likedFlavours: List<Flavour>
)
