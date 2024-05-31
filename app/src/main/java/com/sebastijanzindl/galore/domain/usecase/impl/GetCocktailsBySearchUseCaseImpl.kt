package com.sebastijanzindl.galore.domain.usecase.impl

import com.sebastijanzindl.galore.data.repository.CocktailRepository
import com.sebastijanzindl.galore.domain.models.MultipleCocktailsResponse
import com.sebastijanzindl.galore.domain.usecase.GetCocktailsBySearchUseCase
import io.ktor.client.call.body
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCocktailsBySearchUseCaseImpl @Inject constructor(
    private val cocktailRepository: CocktailRepository
) : GetCocktailsBySearchUseCase {
    override suspend fun execute(input: GetCocktailsBySearchUseCase.Input): GetCocktailsBySearchUseCase.Output =
        withContext(Dispatchers.IO) {
            val response = cocktailRepository.getCocktailsBySearch(input.query);

            if(response.status.value in 200..299) {
                val body = response.body<MultipleCocktailsResponse>()
                GetCocktailsBySearchUseCase.Output(body.cocktails)
            } else {
                GetCocktailsBySearchUseCase.Output(null)
            }
        }
}