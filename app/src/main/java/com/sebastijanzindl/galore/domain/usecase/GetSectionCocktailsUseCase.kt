package com.sebastijanzindl.galore.domain.usecase

import com.sebastijanzindl.galore.domain.models.Cocktail

enum class CocktailDbSectionName(val sectionName: String) {
    RECENT("recent"),
    POPULAR("popular"),
    RANDOM("random")

}

interface GetSectionCocktailsUseCase : UseCase<GetSectionCocktailsUseCase.Input, GetSectionCocktailsUseCase.Output> {
    class Input(val sectionName: CocktailDbSectionName)

    class Output(val result: List<Cocktail>?)
}