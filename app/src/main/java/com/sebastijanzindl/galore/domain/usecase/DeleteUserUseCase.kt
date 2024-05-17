package com.sebastijanzindl.galore.domain.usecase

interface DeleteUserUseCase: UseCase<DeleteUserUseCase.Input, DeleteUserUseCase.Output> {
    class Input
    sealed class Output {
        object Success: Output()

        object Failure: Output()
    }
}