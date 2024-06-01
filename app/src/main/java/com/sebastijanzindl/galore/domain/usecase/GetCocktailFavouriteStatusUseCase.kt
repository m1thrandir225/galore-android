package com.sebastijanzindl.galore.domain.usecase

interface GetCocktailFavouriteStatusUseCase : UseCase<GetCocktailFavouriteStatusUseCase.Input, GetCocktailFavouriteStatusUseCase.Output>{
    class Input(val cocktailId: String, val userId: String)

    sealed class Output() {
        object Success: Output()

        object Failure: Output()
    }
}