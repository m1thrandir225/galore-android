package com.sebastijanzindl.galore.data.repository

import com.sebastijanzindl.galore.domain.models.Cocktail

interface UserLikedCocktailRepository {

    suspend fun getAllLikedCocktails(): List<Cocktail>

    suspend fun likeACocktailByName(cocktailName: String): Cocktail

    suspend fun likeACocktailById(cocktailId: String): Cocktail

    suspend fun unlikeACocktailByName(cocktailName: String): Cocktail

    suspend fun unlikeACocktailById(cocktailId: String): Cocktail
}