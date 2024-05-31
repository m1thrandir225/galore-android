package com.sebastijanzindl.galore.domain.usecase.impl

import com.sebastijanzindl.galore.data.repository.CocktailRepository
import com.sebastijanzindl.galore.domain.models.MultipleCocktailsResponse
import com.sebastijanzindl.galore.domain.usecase.GetPopularCocktailsUseCase
import io.ktor.client.call.body
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPopularCocktailsUseCaseImpl @Inject constructor(
    private val cocktailRepository: CocktailRepository
): GetPopularCocktailsUseCase {
    override suspend fun execute(input: GetPopularCocktailsUseCase.Input): GetPopularCocktailsUseCase.Output =
        withContext(Dispatchers.IO) {
            val response = cocktailRepository.getPopularCocktails()

            if(response.status.value in 200..299) {
                val body = response.body<MultipleCocktailsResponse>()

                GetPopularCocktailsUseCase.Output(body.cocktails)
            } else {
                GetPopularCocktailsUseCase.Output(null)
            }
        }

}