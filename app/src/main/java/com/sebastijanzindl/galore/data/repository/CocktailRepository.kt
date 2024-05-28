package com.sebastijanzindl.galore.data.repository

import com.sebastijanzindl.galore.data.network.GenerateCocktailResponse
import com.sebastijanzindl.galore.domain.models.Cocktail
import com.sebastijanzindl.galore.domain.models.UserMadeCocktail
import io.ktor.client.statement.HttpResponse

interface CocktailRepository {
  suspend fun getAllCocktails(): List<Cocktail?>
  suspend fun getSingleCocktail(cocktailName: String): Cocktail?

  suspend fun generateCocktail(prompt: String, authorizationToken: String): GenerateCocktailResponse?

  suspend fun getGeneratedCocktail(cocktailId: String): HttpResponse

  suspend fun getYourGeneratedCocktails(userId: String): List<UserMadeCocktail>
}
