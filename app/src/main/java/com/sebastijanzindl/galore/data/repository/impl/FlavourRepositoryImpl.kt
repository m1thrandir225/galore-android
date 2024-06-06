package com.sebastijanzindl.galore.data.repository.impl

import com.sebastijanzindl.galore.data.network.response.UserFlavourResponse
import com.sebastijanzindl.galore.data.repository.FlavourRepository
import com.sebastijanzindl.galore.domain.models.Flavour
import com.sebastijanzindl.galore.domain.models.UserLikedFlavour
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Columns
import javax.inject.Inject


class FlavourRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest
): FlavourRepository {
    override suspend fun getAllFlavours(): List<Flavour> {
       return postgrest.from("flavours").select().decodeList<Flavour>()
    }

    override suspend fun getUserFlavours(userId: String): List<Flavour> {
        val columns = Columns.raw("""
            user_id,
            flavours(*)
        """.trimIndent())
        val result =  postgrest.from("user_liked_flavours")
            .select(columns) {
                filter {
                    eq("user_id", userId)
                }
            }.decodeList<UserFlavourResponse>()

        return result.map {
            it.flavour
        }
    }
    override suspend fun addFlavoursToFavourites(flavourIds: List<String>, userId: String): List<UserLikedFlavour> {
        val userLikedFlavours = flavourIds.map { flavourId ->
            UserLikedFlavour(
                userId = userId,
                flavourId = flavourId
            )
        }
        return postgrest.from("user_liked_flavours").insert(userLikedFlavours) {
            select()
        }.decodeList<UserLikedFlavour>()
    }
}