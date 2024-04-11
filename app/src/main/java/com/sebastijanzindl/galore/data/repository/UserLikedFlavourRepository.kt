package com.sebastijanzindl.galore.data.repository

import com.sebastijanzindl.galore.domain.models.Flavour

interface UserLikedFlavourRepository {
    suspend fun getAllLikedFlavours(): List<Flavour>

    suspend fun likeAFlavourByName(flavourName: String): Flavour

    suspend fun likeAFlavourById(flavourId: String): Flavour

    suspend fun unlikeAFlavourByName(flavourName: String): Flavour

    suspend fun unlikeAFlavourById(flavourId: String): Flavour
}