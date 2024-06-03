package com.sebastijanzindl.galore.domain.usecase

import com.sebastijanzindl.galore.domain.models.Flavour

interface GetUserFlavoursUseCase: UseCase<GetUserFlavoursUseCase.Input, GetUserFlavoursUseCase.Output> {
    class Input(val userId: String);
    sealed class Output(val result: List<Flavour>?) {
        class Success(successResult: List<Flavour>) : Output(successResult);

        data object Failure: Output(null);
    }
}