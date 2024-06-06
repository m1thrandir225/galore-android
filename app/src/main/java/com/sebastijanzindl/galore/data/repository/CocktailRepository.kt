package com.sebastijanzindl.galore.data.repository

import com.sebastijanzindl.galore.domain.models.Cocktail
import com.sebastijanzindl.galore.domain.models.UserLikedCocktail
import com.sebastijanzindl.galore.domain.models.UserMadeCocktail
import io.ktor.client.statement.HttpResponse

interface CocktailRepository {
  suspend fun getAllCocktails(): List<Cocktail?>

  suspend fun getDailyHomeSections(authorizationToken: String): HttpResponse

  suspend fun generateCocktail(prompt: String, authorizationToken: String): HttpResponse

  suspend fun getGeneratedCocktail(cocktailId: String): HttpResponse

  suspend fun getYourGeneratedCocktails(userId: String): List<UserMadeCocktail>

  suspend fun getLikedCocktails(): HttpResponse

  suspend fun getSingleCocktailFromCocktailDb(id: String): HttpResponse

  suspend fun getSectionCocktails(sectionName: String): HttpResponse

  suspend fun getCocktailsBySearch(query: String): HttpResponse

  suspend fun addCocktailToFavourites(cocktailId: String, userId: String): UserLikedCocktail?
  suspend fun removeCocktailFromFavourites(cocktailId: String, userId: String): UserLikedCocktail?

  suspend fun getCocktailFavouriteStatus(cocktailId: String, userId: String): UserLikedCocktail?
}
