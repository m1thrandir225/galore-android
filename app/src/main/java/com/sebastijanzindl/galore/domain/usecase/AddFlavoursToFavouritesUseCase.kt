package com.sebastijanzindl.galore.domain.usecase

interface AddFlavoursToFavouritesUseCase: UseCase<AddFlavoursToFavouritesUseCase.Input, AddFlavoursToFavouritesUseCase.Output> {
    class Input(val flavoursIds: List<String>, val userId: String)
    sealed class Output {
        object Success: Output();

        object Failure: Output();
    }
}