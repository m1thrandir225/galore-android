package com.sebastijanzindl.galore.data.repository.impl

import com.sebastijanzindl.galore.data.network.KtorAPIService
import com.sebastijanzindl.galore.data.repository.CocktailRepository
import com.sebastijanzindl.galore.domain.models.Cocktail
import com.sebastijanzindl.galore.domain.models.UserLikedCocktail
import com.sebastijanzindl.galore.domain.models.UserMadeCocktail
import io.github.jan.supabase.functions.Functions
import io.github.jan.supabase.postgrest.Postgrest
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import javax.inject.Inject

class CocktailRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest,
    private val edgeFunctions: Functions,
    private val ktorApiService: KtorAPIService
) : CocktailRepository {
    override suspend fun getAllCocktails(): List<Cocktail?> {
        return postgrest.from("cocktails").select().decodeList<Cocktail>()
    }

    override suspend fun getDailyHomeSections(authorizationToken: String): HttpResponse {
        return ktorApiService.getDailyHome(
            token = authorizationToken
        )
    }

    override suspend fun generateCocktail(prompt: String, authorizationToken: String): HttpResponse {

         return ktorApiService.generateCocktail(
            prompt = prompt,
             token = authorizationToken,
        )
    }

    override suspend fun getGeneratedCocktail(cocktailId: String): HttpResponse {
        return edgeFunctions.invoke(
            function = "get-generated-cocktail",
            body = buildJsonObject {
                put("cocktailId", cocktailId)
            }
        )
    }

    override suspend fun getYourGeneratedCocktails(userId: String): List<UserMadeCocktail> {
        return postgrest.from("user_created_cocktails")
            .select()
            {
                filter {
                    eq("user_id", userId)
                }
            }.decodeList<UserMadeCocktail>()

    }

    override suspend fun getLikedCocktails(): HttpResponse {
        return edgeFunctions.invoke(
            function = "get-user-liked-cocktails"
        )
    }

    override suspend fun getSingleCocktailFromCocktailDb(id: String): HttpResponse {
        return edgeFunctions.invoke(
            function = "get-api-cocktail",
            body = buildJsonObject {
                put("cocktailId", id)
            }
        )
    }

    override suspend fun getSectionCocktails(sectionName: String): HttpResponse {
        return  edgeFunctions.invoke(
            function = "get-section-cocktails",
            body = buildJsonObject {
                put("section", sectionName)
            }
        )
    }

    override suspend fun getCocktailsBySearch(query: String): HttpResponse {
        return edgeFunctions.invoke(
            function = "get-cocktails-search",
            body = buildJsonObject {
                put("query", query)
            }
        )
    }

    override suspend fun addCocktailToFavourites(cocktailId: String, userId: String): UserLikedCocktail? {
        return postgrest.from("user_liked_cocktails")
            .insert(UserLikedCocktail(cocktailId = cocktailId, userId = userId)) {
                select()
            }.decodeSingleOrNull<UserLikedCocktail>()
    }

    override suspend fun removeCocktailFromFavourites(
        cocktailId: String,
        userId: String
    ): UserLikedCocktail? {
       return postgrest.from("user_liked_cocktails").delete {
           select()
           filter {
               eq("cocktail_id", cocktailId)
               eq("user_id", userId)
           }
       }.decodeSingleOrNull<UserLikedCocktail>()
    }

    override suspend fun getCocktailFavouriteStatus(
        cocktailId: String,
        userId: String
    ): UserLikedCocktail? {
      return postgrest.from("user_liked_cocktails")
          .select()
          {
              filter {
                  eq("user_id", userId)
                  eq("cocktail_id", cocktailId)
              }
          }.decodeSingleOrNull<UserLikedCocktail>()
    }
}