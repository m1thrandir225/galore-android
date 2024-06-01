package com.sebastijanzindl.galore.domain.usecase.impl

import com.sebastijanzindl.galore.data.repository.CocktailRepository
import com.sebastijanzindl.galore.domain.usecase.GetCocktailFavouriteStatusUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCocktailFavouriteStatusUseCaseImpl @Inject constructor(
    private val cocktailRepository: CocktailRepository
) : GetCocktailFavouriteStatusUseCase {
    override suspend fun execute(input: GetCocktailFavouriteStatusUseCase.Input): GetCocktailFavouriteStatusUseCase.Output =
        withContext(Dispatchers.IO) {
            val response = cocktailRepository.getCocktailFavouriteStatus(cocktailId = input.cocktailId, userId = input.userId)

            if(response != null) {
                GetCocktailFavouriteStatusUseCase.Output.Success
            } else {
                GetCocktailFavouriteStatusUseCase.Output.Failure
            }
        }
}