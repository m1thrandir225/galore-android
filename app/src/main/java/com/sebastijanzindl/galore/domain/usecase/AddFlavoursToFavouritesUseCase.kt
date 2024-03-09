package com.sebastijanzindl.galore.domain.usecase

import com.sebastijanzindl.galore.domain.models.Flavour

interface AddFlavoursToFavouritesUseCase: UseCase<AddFlavoursToFavouritesUseCase.Input, AddFlavoursToFavouritesUseCase.Output> {
    class Input(val flavours: List<Flavour>)
    sealed class Output {
        object Success: Output();

        object Failure: Output();
    }
}