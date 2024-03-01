package com.sebastijanzindl.galore.domain.usecase

interface SignUpUseCase: UseCase<SignUpUseCase.Input, SignUpUseCase.Output> {
    class Input(val email: String, val password: String, val fullName: String)
    sealed class Output {
        object Success: Output()
        object Failure: Output()
    }
}