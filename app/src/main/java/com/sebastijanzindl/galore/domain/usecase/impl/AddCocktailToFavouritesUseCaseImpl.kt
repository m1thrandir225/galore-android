package com.sebastijanzindl.galore.domain.usecase.impl

import com.sebastijanzindl.galore.data.repository.CocktailRepository
import com.sebastijanzindl.galore.domain.usecase.AddCocktailToFavouritesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddCocktailToFavouritesUseCaseImpl @Inject constructor(
    private val cocktailRepository: CocktailRepository
) : AddCocktailToFavouritesUseCase {
    override suspend fun execute(input: AddCocktailToFavouritesUseCase.Input): AddCocktailToFavouritesUseCase.Output =
        withContext(Dispatchers.IO) {
            val response = cocktailRepository.addCocktailToFavourites(input.cocktailId, input.userId)
            if(response != null) {
                AddCocktailToFavouritesUseCase.Output.Success
            } else {
                AddCocktailToFavouritesUseCase.Output.Failure
            }
        }
}