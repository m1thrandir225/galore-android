package com.sebastijanzindl.galore.data.repository

import com.sebastijanzindl.galore.domain.models.Flavour
import com.sebastijanzindl.galore.domain.models.UserLikedFlavour

interface FlavourRepository {
    suspend fun getAllFlavours(): List<Flavour>

    suspend fun getUserFlavours(userId: String): List<Flavour>

    suspend fun addFlavoursToFavourites(flavourIds: List<String>, userId: String): List<UserLikedFlavour>

}