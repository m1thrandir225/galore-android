package com.sebastijanzindl.galore.domain.usecase

interface GenerateCocktailUseCase : UseCase<GenerateCocktailUseCase.Input, GenerateCocktailUseCase.Output> {
    class Input(val prompt: String, val token: String)

    class Output(val cocktailId: String?)
}