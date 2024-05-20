package com.sebastijanzindl.galore.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCocktailListResponse(
    @SerialName ("drinks")
    val drinks: MutableList<CocktailDto>,
)
