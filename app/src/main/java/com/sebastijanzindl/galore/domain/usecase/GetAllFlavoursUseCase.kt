package com.sebastijanzindl.galore.domain.usecase

interface GetAllFlavoursUseCase: UseCase<GetAllFlavoursUseCase.Input, GetAllFlavoursUseCase.Output> {
    class Input

    sealed class Output {
        object Success: Output()

        object Failure: Output();
    }
}