package com.sebastijanzindl.galore.data.repository

import com.sebastijanzindl.galore.domain.models.Cocktail

interface CocktailRepository {
    suspend fun getAllCocktails(): List<Cocktail>
    suspend fun getCocktailsByFlavour(flavourName: String): List<Cocktail>
    suspend fun getCocktailsByKeyword(query: String): List<Cocktail>
    suspend fun getSingleCocktail(cocktailName: String): Cocktail
}