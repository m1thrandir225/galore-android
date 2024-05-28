package com.sebastijanzindl.galore.domain.usecase

import com.sebastijanzindl.galore.domain.models.UserMadeCocktail

interface GetAllUserMadeCocktails: UseCase<GetAllUserMadeCocktails.Input, GetAllUserMadeCocktails.Output> {
    class Input(val userId: String);

     class Output(val result: List<UserMadeCocktail>)
}