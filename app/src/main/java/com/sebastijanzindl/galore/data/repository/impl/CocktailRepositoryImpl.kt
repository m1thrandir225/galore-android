package com.sebastijanzindl.galore.data.repository.impl

import com.sebastijanzindl.galore.data.repository.CocktailRepository
import com.sebastijanzindl.galore.domain.models.Cocktail
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Columns

class CocktailRepositoryImpl(
    private val postgrest: Postgrest
) : CocktailRepository {
    override suspend fun getAllCocktails(): List<Cocktail?> {
        return postgrest.from("cocktails").select().decodeList<Cocktail>()
    }
    override suspend fun getCocktailsByFlavour(flavourName: String): List<Cocktail?> {
        val columns = Columns.raw("""
            *,
            cocktail_flavours!inner (
                name
            )
        """.trimIndent())

        return postgrest.from("cocktails").select(
            columns = columns
        ) {
            filter {
                eq("cocktail_flavours.name", flavourName)
            }
        }.decodeList<Cocktail>()
    }
    override suspend fun getCocktailsByKeyword(query: String): List<Cocktail?> {
        TODO("Not yet implemented")
    }
    override suspend fun getSingleCocktail(cocktailName: String): Cocktail? {
        return postgrest.from("cocktails")
            .select() {
                filter {
                    eq("name", cocktailName)
                }
            }.decodeSingleOrNull<Cocktail>()
    }
}