package com.sebastijanzindl.galore.data.network

import com.sebastijanzindl.galore.data.network.dto.GetCocktailListResponse
import kotlinx.coroutines.Deferred
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

private const val BASE_URL = "https://docs.rapidapi.com/docs/keys"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(
        Json.asConverterFactory(
            "application/json; charset=UTF8".toMediaType())
    )
    .baseUrl(BASE_URL)
    .build()
interface CocktailsApiService {
    @Headers(
        "x-rapidapi-host: 0028410dbamsh07bc54033e8db18p1518bcjsn3d13bda157d3",
        "x-rapidapi-key: the-cocktail-db.p.rapidapi.com"
    )
    @GET("/randomselection.php")
    fun getRandomCocktails(
    ): Deferred<GetCocktailListResponse>
}

object Api {
    val retrofitService: CocktailsApiService by lazy {
        retrofit.create(CocktailsApiService::class.java)
    }
}