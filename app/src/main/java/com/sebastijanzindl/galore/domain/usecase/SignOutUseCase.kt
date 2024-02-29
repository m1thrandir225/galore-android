package com.sebastijanzindl.galore.domain.usecase

interface  SignOutUseCase: UseCase<SignOutUseCase.Input, SignOutUseCase.Output>{
    class Input

    sealed class Output {
        object Success: Output()

        object  Failure: Output()
    }
}