package com.sebastijanzindl.galore.data.network.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerateCocktailRequest (
    @SerialName("prompt")
    val prompt: String
)