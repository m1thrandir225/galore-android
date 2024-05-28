package com.sebastijanzindl.galore.domain.models

data class Section(
    val cocktails: List<CocktailCardInfo>,
    val tagName: String,
    val isFeatured: Boolean = false,
)