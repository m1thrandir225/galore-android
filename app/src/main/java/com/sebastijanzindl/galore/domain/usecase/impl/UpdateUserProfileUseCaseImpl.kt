package com.sebastijanzindl.galore.domain.usecase.impl

import com.sebastijanzindl.galore.data.repository.UserProfileRepository
import com.sebastijanzindl.galore.domain.usecase.UpdateUserProfileUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateUserProfileUseCaseImpl @Inject constructor(
    private val userProfileRepository: UserProfileRepository
) : UpdateUserProfileUseCase {
    override suspend fun execute(input: UpdateUserProfileUseCase.Input): UpdateUserProfileUseCase.Output =
        withContext(Dispatchers.IO) {
            val result = userProfileRepository.updateUserProfile(
                updatedProfile = input.updatedProfile
            )
            UpdateUserProfileUseCase.Output(result)
        }
}