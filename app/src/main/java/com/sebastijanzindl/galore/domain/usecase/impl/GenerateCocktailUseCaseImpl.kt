package com.sebastijanzindl.galore.domain.usecase.impl

import com.sebastijanzindl.galore.data.network.response.GenerateCocktailResponse
import com.sebastijanzindl.galore.data.repository.CocktailRepository
import com.sebastijanzindl.galore.domain.usecase.GenerateCocktailUseCase
import io.ktor.client.call.body
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GenerateCocktailUseCaseImpl @Inject constructor(
    private val cocktailRepository: CocktailRepository
) : GenerateCocktailUseCase{
    override suspend fun execute(input: GenerateCocktailUseCase.Input): GenerateCocktailUseCase.Output  =
        withContext(Dispatchers.IO) {
            val response = cocktailRepository.generateCocktail(input.prompt, input.token);

            if(response.status.value in 200..299) {
                val body = response.body<GenerateCocktailResponse>()
                GenerateCocktailUseCase.Output(body.generatedCocktailId)
            } else {
                GenerateCocktailUseCase.Output(null)
            }
        }
}