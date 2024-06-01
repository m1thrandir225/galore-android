package com.sebastijanzindl.galore.domain.usecase.impl

import com.sebastijanzindl.galore.data.repository.CocktailRepository
import com.sebastijanzindl.galore.domain.models.MultipleCocktailsResponse
import com.sebastijanzindl.galore.domain.usecase.CocktailDbSectionName
import com.sebastijanzindl.galore.domain.usecase.GetSectionCocktailsUseCase
import io.ktor.client.call.body
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPopularCocktailsUseCaseImpl @Inject constructor(
    private val cocktailRepository: CocktailRepository
): GetSectionCocktailsUseCase {
    override suspend fun execute(input: GetSectionCocktailsUseCase.Input): GetSectionCocktailsUseCase.Output =
        withContext(Dispatchers.IO) {
            val sectionName = when(input.sectionName) {
                CocktailDbSectionName.RECENT -> {
                    "recent"
                }
                CocktailDbSectionName.POPULAR -> {
                    "popular"
                }
                CocktailDbSectionName.RANDOM -> {
                    "random"
                }
            }

            val response = cocktailRepository.getSectionCocktails(sectionName)

            if(response.status.value in 200..299) {
                val body = response.body<MultipleCocktailsResponse>()

                GetSectionCocktailsUseCase.Output(body.cocktails)
            } else {
                GetSectionCocktailsUseCase.Output(null)
            }
        }

}