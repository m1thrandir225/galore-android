package com.sebastijanzindl.galore.domain.usecase.impl

import com.sebastijanzindl.galore.data.repository.CocktailRepository
import com.sebastijanzindl.galore.domain.models.MultipleCocktailsResponse
import com.sebastijanzindl.galore.domain.usecase.GetUserLikedCocktailsUseCase
import io.ktor.client.call.body
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserLikedCocktailsUseCaseImpl @Inject constructor(
    private val cocktailRepository: CocktailRepository
) : GetUserLikedCocktailsUseCase {
    override suspend fun execute(input: GetUserLikedCocktailsUseCase.Input): GetUserLikedCocktailsUseCase.Output =
        withContext(Dispatchers.IO) {
            val response = cocktailRepository.getLikedCocktails()

            if(response.status.value in 200..299) {
                val body = response.body<MultipleCocktailsResponse>()

                GetUserLikedCocktailsUseCase.Output(result = body.cocktails)
            } else {
                GetUserLikedCocktailsUseCase.Output(result = emptyList())
            }
        }
}