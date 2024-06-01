package com.sebastijanzindl.galore.domain.usecase.impl

import com.sebastijanzindl.galore.data.repository.CocktailRepository
import com.sebastijanzindl.galore.domain.usecase.RemoveCocktailFromFavouritesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoveCocktailFromFavouritesUseCaseImpl @Inject constructor(
    private val cocktailRepository: CocktailRepository
) : RemoveCocktailFromFavouritesUseCase {
    override suspend fun execute(input: RemoveCocktailFromFavouritesUseCase.Input): RemoveCocktailFromFavouritesUseCase.Output =
        withContext(Dispatchers.IO) {
            val response = cocktailRepository.removeCocktailFromFavourites(input.cocktailId, input.userId)
            if(response != null) {
                RemoveCocktailFromFavouritesUseCase.Output.Success
            } else {
                RemoveCocktailFromFavouritesUseCase.Output.Failure
            }
        }
}