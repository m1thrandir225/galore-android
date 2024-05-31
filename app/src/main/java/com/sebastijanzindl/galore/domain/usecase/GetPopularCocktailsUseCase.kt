package com.sebastijanzindl.galore.domain.usecase

import com.sebastijanzindl.galore.domain.models.Cocktail

interface GetPopularCocktailsUseCase : UseCase<GetPopularCocktailsUseCase.Input, GetPopularCocktailsUseCase.Output> {
    class Input()

    class Output(val result: List<Cocktail>?)
}