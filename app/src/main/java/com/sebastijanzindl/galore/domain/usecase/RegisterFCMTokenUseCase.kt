package com.sebastijanzindl.galore.domain.usecase

interface RegisterFCMTokenUseCase : UseCase<RegisterFCMTokenUseCase.Input, RegisterFCMTokenUseCase.Output> {
    class Input(val token: String, val userId: String, val deviceId: String)

    open class Output {
        object Success: Output()

        object AlreadyExists: Output()

        object Failure: Output()
    }
}