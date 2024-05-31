package com.sebastijanzindl.galore.domain.usecase.impl

import com.sebastijanzindl.galore.data.repository.CocktailRepository
import com.sebastijanzindl.galore.domain.models.SingleCocktailResponse
import com.sebastijanzindl.galore.domain.usecase.GetSingleCocktailUseCase
import io.ktor.client.call.body
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetSingleCocktailUseCaseImpl @Inject constructor(
    private val cocktailRepository: CocktailRepository
) : GetSingleCocktailUseCase {
    override suspend fun execute(input: GetSingleCocktailUseCase.Input): GetSingleCocktailUseCase.Output =
        withContext(Dispatchers.IO) {
            val response = cocktailRepository.getSingleCocktailFromCocktailDb(input.id);

            if(response.status.value in 200..299) {
                val body = response.body<SingleCocktailResponse>()
                GetSingleCocktailUseCase.Output(body.cocktail)
            } else {
                GetSingleCocktailUseCase.Output(null)
            }
        }
}