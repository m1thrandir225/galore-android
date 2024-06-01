package com.sebastijanzindl.galore.domain.usecase

interface AddCocktailToFavouritesUseCase : UseCase<AddCocktailToFavouritesUseCase.Input, AddCocktailToFavouritesUseCase.Output> {
    class Input(val cocktailId: String, val userId: String)

    sealed class Output {
        object Success: Output();

        object Failure: Output();
    }
}