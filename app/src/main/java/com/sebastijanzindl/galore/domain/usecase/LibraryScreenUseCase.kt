package com.sebastijanzindl.galore.domain.usecase

import com.sebastijanzindl.galore.domain.models.UserMadeCocktail

interface LibraryScreenUseCase : UseCase<LibraryScreenUseCase.Input, LibraryScreenUseCase.Output>{
    class Input();
    class Output(val result: List<UserMadeCocktail?>?);
}