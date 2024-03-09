package com.sebastijanzindl.galore.domain.usecase

interface GetUserFlavoursUseCase: UseCase<GetUserFlavoursUseCase.Input, GetUserFlavoursUseCase.Output> {
    class Input(val userId: String);
    sealed class Output {
        object Success: Output();

        object Failure: Output();
    }
}