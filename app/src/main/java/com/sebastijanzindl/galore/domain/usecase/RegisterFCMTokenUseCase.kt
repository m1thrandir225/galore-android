package com.sebastijanzindl.galore.domain.usecase

interface RegisterFCMTokenUseCase : UseCase<RegisterFCMTokenUseCase.Input, RegisterFCMTokenUseCase.Output> {
    class Input(val token: String, val userId: String)

    open class Output {
        object Success: Output()

        object Failure: Output()
    }
}