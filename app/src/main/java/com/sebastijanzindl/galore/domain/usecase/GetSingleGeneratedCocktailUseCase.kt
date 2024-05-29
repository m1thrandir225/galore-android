package com.sebastijanzindl.galore.domain.usecase

import com.sebastijanzindl.galore.domain.models.UserMadeCocktail

interface GetSingleGeneratedCocktailUseCase : UseCase<GetSingleGeneratedCocktailUseCase.Input, GetSingleGeneratedCocktailUseCase.Output> {
    class Input(val id: String)

    class Output(val result: UserMadeCocktail?)
}