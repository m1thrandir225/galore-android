package com.sebastijanzindl.galore.domain.usecase

import com.sebastijanzindl.galore.domain.models.Cocktail

interface GetCocktailsBySearchUseCase : UseCase<GetCocktailsBySearchUseCase.Input, GetCocktailsBySearchUseCase.Output> {
    class Input(val query: String)

    class Output(val result: List<Cocktail>?)
}