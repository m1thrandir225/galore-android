package com.sebastijanzindl.galore.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Cocktail(
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("image")
    val image: String,

    @SerialName("steps")
    val steps: String,

    @SerialName("ingredients")
    val ingredients: String,

    @SerialName("tags")
    val tags: Array<String>,

    @SerialName("created_at")
    val createdAt: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cocktail

        if (id != other.id) return false
        if (name != other.name) return false
        if (image != other.image) return false
        if (steps != other.steps) return false
        if (ingredients != other.ingredients) return false
        if (!tags.contentEquals(other.tags)) return false
        if (createdAt != other.createdAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + image.hashCode()
        result = 31 * result + steps.hashCode()
        result = 31 * result + ingredients.hashCode()
        result = 31 * result + tags.contentHashCode()
        result = 31 * result + createdAt.hashCode()
        return result
    }
}
