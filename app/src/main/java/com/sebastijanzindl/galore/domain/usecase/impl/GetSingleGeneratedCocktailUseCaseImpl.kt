package com.sebastijanzindl.galore.domain.usecase.impl

import com.sebastijanzindl.galore.data.repository.CocktailRepository
import com.sebastijanzindl.galore.domain.models.UserMadeCocktail
import com.sebastijanzindl.galore.domain.usecase.GetSingleGeneratedCocktailUseCase
import io.ktor.client.call.body
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetSingleGeneratedCocktailUseCaseImpl @Inject constructor(
    private val cocktailRepository: CocktailRepository
) : GetSingleGeneratedCocktailUseCase {
    override suspend fun execute(input: GetSingleGeneratedCocktailUseCase.Input): GetSingleGeneratedCocktailUseCase.Output =
        withContext(Dispatchers.IO) {
            val response = cocktailRepository.getGeneratedCocktail(input.id);

            if(response.status.value in 200..299) {
                val body = response.body<UserMadeCocktail>()
                GetSingleGeneratedCocktailUseCase.Output(body)
            } else {
                GetSingleGeneratedCocktailUseCase.Output(null)

            }
        }
}