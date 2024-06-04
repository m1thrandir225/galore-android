package com.sebastijanzindl.galore.data.repository.impl

import com.sebastijanzindl.galore.data.network.request.GenerateCocktailRequest
import com.sebastijanzindl.galore.data.repository.GenerateCocktailRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import javax.inject.Inject

class GenerateCocktailRepositoryImpl @Inject constructor(private val httpClient: HttpClient):
    GenerateCocktailRepository {
    override suspend fun generateCocktail(prompt: String, token: String):  HttpResponse {
        return httpClient.post("/generate-cocktail") {
            headers {
                append(HttpHeaders.Authorization, token)
            }
            contentType(ContentType.Application.Json)
            setBody(
                GenerateCocktailRequest(prompt)
            )
        }
    }
}