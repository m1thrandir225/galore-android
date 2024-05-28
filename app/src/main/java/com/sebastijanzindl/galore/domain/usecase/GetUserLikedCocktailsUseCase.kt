package com.sebastijanzindl.galore.domain.usecase

import com.sebastijanzindl.galore.domain.models.Cocktail

interface GetUserLikedCocktailsUseCase : UseCase<GetUserLikedCocktailsUseCase.Input, GetUserLikedCocktailsUseCase.Output> {
    class Input()
    class Output(val result: List<Cocktail>)
}