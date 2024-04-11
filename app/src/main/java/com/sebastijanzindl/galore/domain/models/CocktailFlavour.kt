package com.sebastijanzindl.galore.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CocktailFlavour(
    @SerialName("flavour_id")
    val flavourId: String,

    @SerialName("cocktail_id")
    val cocktailId: String
)

