package com.sebastijanzindl.galore.data.repository

import com.sebastijanzindl.galore.domain.models.Flavour
import java.util.UUID

interface FlavourRepository {
    suspend fun getAllFlavours(): List<Flavour>
    suspend fun getUserFlavours(userId: String): List<Flavour>
    suspend fun addFlavoursToFavourites(flavours: List<Flavour>): List<Flavour>
}