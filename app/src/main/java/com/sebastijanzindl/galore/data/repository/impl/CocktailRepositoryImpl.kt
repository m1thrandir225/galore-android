package com.sebastijanzindl.galore.data.repository.impl

import com.sebastijanzindl.galore.data.repository.CocktailRepository
import com.sebastijanzindl.galore.domain.models.Cocktail
import io.github.jan.supabase.postgrest.Postgrest

class CocktailRepositoryImpl(
    private val postgrest: Postgrest
) : CocktailRepository {
    override suspend fun getAllCocktails(): List<Cocktail> {
        TODO("Not yet implemented")
    }
    override suspend fun getCocktailsByFlavour(flavourName: String): List<Cocktail> {
        TODO("Not yet implemented")
    }
    override suspend fun getCocktailsByKeyword(query: String): List<Cocktail> {
        TODO("Not yet implemented")
    }
    override suspend fun getSingleCocktail(cocktailName: String): Cocktail {
        TODO("Not yet implemented")
    }
}