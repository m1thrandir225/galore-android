package com.sebastijanzindl.galore.data.repository.impl

import com.sebastijanzindl.galore.data.network.ApiService
import com.sebastijanzindl.galore.data.network.GenerateCocktailResponse
import com.sebastijanzindl.galore.data.repository.CocktailRepository
import com.sebastijanzindl.galore.domain.models.Cocktail
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
    private val honoService: ApiService
) : CocktailRepository {
    override suspend fun getAllCocktails(): List<Cocktail?> {
        return postgrest.from("cocktails").select().decodeList<Cocktail>()
    }
    override suspend fun getSingleCocktail(cocktailName: String): Cocktail? {
        return postgrest.from("cocktails")
            .select() {
                filter {
                    eq("name", cocktailName)
                }
            }.decodeSingleOrNull<Cocktail>()
    }

    override suspend fun generateCocktail(prompt: String, authorizationToken: String): GenerateCocktailResponse? {
        return honoService.generateCocktail(
            prompt = prompt,
            authorization = authorizationToken,
        ).body();
    }

    override suspend fun getGeneratedCocktail(cocktailId: String): HttpResponse {
        return edgeFunctions.invoke(
            function = "get-generated-cocktail",
            body = buildJsonObject {
                put("cocktail_id", cocktailId)
            },
        )
    }

    override suspend fun getYourGeneratedCocktails(userId: String): List<UserMadeCocktail?> {
        return postgrest.from("user_created_cocktails")
            .select()
            {
                filter {
                    eq("user_id", userId)
                }
            }.decodeList<UserMadeCocktail>()

    }
}