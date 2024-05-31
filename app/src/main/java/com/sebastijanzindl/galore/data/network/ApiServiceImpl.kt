package com.sebastijanzindl.galore.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.inject.Inject

@Serializable
data class GenerateCocktailRequest (
    @SerialName("prompt")
    val prompt: String
)

class ApiServiceImpl @Inject constructor(private val httpClient: HttpClient): ApiService{
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