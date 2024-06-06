package com.sebastijanzindl.galore.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Section(
    @SerialName("cocktails")
    val cocktails: List<CocktailCardInfo>,

    @SerialName("section_name")
    val tagName: String,

    @SerialName("is_featured")
    val isFeatured: Boolean = false,

    val generatedCocktailsSection: Boolean = false
)