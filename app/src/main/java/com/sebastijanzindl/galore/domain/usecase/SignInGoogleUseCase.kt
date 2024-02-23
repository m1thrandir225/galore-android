package com.sebastijanzindl.galore.domain.usecase

interface SignInGoogleUseCase: UseCase<SignInGoogleUseCase.Input, SignInGoogleUseCase.Output>{
    class Input(val token: String, val rawNonce: String)

    class Output
}