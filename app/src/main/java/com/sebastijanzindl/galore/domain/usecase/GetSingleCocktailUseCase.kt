package com.sebastijanzindl.galore.domain.usecase

import com.sebastijanzindl.galore.domain.models.Cocktail

interface GetSingleCocktailUseCase: UseCase<GetSingleCocktailUseCase.Input, GetSingleCocktailUseCase.Output> {
    class Input(val id: String)

    class Output(val result: Cocktail?)
}