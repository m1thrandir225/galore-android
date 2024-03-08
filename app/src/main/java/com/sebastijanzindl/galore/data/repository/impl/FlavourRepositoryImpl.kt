package com.sebastijanzindl.galore.data.repository.impl

import com.sebastijanzindl.galore.data.repository.FlavourRepository
import com.sebastijanzindl.galore.domain.models.Flavour
import io.github.jan.supabase.postgrest.Postgrest
import java.util.UUID

class FlavourRepositoryImpl(
    private val postgrest: Postgrest
): FlavourRepository {
    override suspend fun getAllFlavours(): List<Flavour> {
       return postgrest.from("flavours").select().decodeList<Flavour>()
    }
    override suspend fun addFlavoursToFavourites(flavours: List<Flavour>): List<Flavour> {
        return postgrest.from("flavours").insert(flavours) {
            select()
        }.decodeList<Flavour>()
    }
    override suspend fun getUserFlavours(userId: UUID): List<Flavour> {
        TODO("Not yet implemented")
    }
}