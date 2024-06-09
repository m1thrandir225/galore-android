package com.sebastijanzindl.galore.domain.usecase

interface GetDeviceFCMTokenUseCase : UseCase<GetDeviceFCMTokenUseCase.Input, GetDeviceFCMTokenUseCase.Output> {
    class Input()

    sealed class Output(val result: String?) {
        class Success(val token: String) : Output(token)

        class Failure(): Output(null)
    }
}