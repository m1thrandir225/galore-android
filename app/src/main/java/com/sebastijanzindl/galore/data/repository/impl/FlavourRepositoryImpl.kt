package com.sebastijanzindl.galore.data.repository.impl

import com.sebastijanzindl.galore.data.repository.FlavourRepository
import com.sebastijanzindl.galore.domain.models.Flavour
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Columns

class FlavourRepositoryImpl(
    private val postgrest: Postgrest
): FlavourRepository {
    override suspend fun getAllFlavours(): List<Flavour> {
       return postgrest.from("flavours").select().decodeList<Flavour>()
    }

    override suspend fun getUserFlavours(userId: String): List<Flavour> {
        val columns = Columns.raw("""
            flavours (
                id,
                name,
                created_at
            )
        """.trimIndent())
        return postgrest.from("user_liked_flavours")
            .select(columns = columns)
            .decodeList<Flavour>()
    }
}