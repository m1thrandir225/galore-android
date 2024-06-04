package com.sebastijanzindl.galore.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserMadeCocktailInstruction(
    @SerialName("instruction")
    val instruction: String,

    @SerialName("instruction_image")
    val instructionImage: String
)