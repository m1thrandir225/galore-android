package com.sebastijanzindl.galore.domain.usecase

interface RemoveCocktailFromFavouritesUseCase : UseCase<RemoveCocktailFromFavouritesUseCase.Input, RemoveCocktailFromFavouritesUseCase.Output> {
    class Input(val cocktailId: String, val userId: String)

    sealed class Output() {
        object Success: Output()
        object Failure: Output()
    }
}