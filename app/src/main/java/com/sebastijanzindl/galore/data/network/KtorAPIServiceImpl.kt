package com.sebastijanzindl.galore.data.network

import com.sebastijanzindl.galore.data.network.request.GenerateCocktailRequest
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import javax.inject.Inject

class KtorAPIServiceImpl @Inject constructor(private val httpClient: HttpClient):
    KtorAPIService {
    override suspend fun generateCocktail(prompt: String, token: String):  HttpResponse {
        return httpClient.post("/generate-cocktail") {
            headers {
                append(HttpHeaders.Authorization, "Bearer $token")
            }
            contentType(ContentType.Application.Json)
            setBody(
                GenerateCocktailRequest(prompt)
            )
        }
    }

    override suspend fun getDailyHome(token: String): HttpResponse {
        return httpClient.get("/get-daily-home-sections") {
            headers {
                append(HttpHeaders.Authorization, "Bearer $token")
            }
            contentType(ContentType.Application.Json)
        }
    }
}