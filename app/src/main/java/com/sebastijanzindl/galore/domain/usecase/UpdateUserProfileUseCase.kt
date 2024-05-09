package com.sebastijanzindl.galore.domain.usecase

import com.sebastijanzindl.galore.domain.models.UserProfile

interface UpdateUserProfileUseCase: UseCase<UpdateUserProfileUseCase.Input, UpdateUserProfileUseCase.Output> {
    class Input(val updatedProfile: UserProfile)

    open class Output(val result: UserProfile?)
}