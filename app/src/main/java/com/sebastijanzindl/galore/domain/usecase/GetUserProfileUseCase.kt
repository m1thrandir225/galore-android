package com.sebastijanzindl.galore.domain.usecase

import com.sebastijanzindl.galore.domain.models.UserProfile
import kotlinx.coroutines.flow.Flow

interface GetUserProfileUseCase: UseCase<GetUserProfileUseCase.Input, GetUserProfileUseCase.Output> {
    class Input()
    open class Output(val result : UserProfile?)
}