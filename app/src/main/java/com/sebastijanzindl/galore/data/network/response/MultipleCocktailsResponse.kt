package com.sebastijanzindl.galore.data.network.response

import com.sebastijanzindl.galore.domain.models.Cocktail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MultipleCocktailsResponse(
    @SerialName("cocktails")
    val cocktails: List<Cocktail>
)