package com.sebastijanzindl.galore.domain.usecase.impl

import com.sebastijanzindl.galore.data.repository.UserProfileRepository
import com.sebastijanzindl.galore.domain.usecase.GetUserProfileUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class GetUserProfileUseCaseImpl @Inject constructor(
    private val userProfileRepository: UserProfileRepository,
) : GetUserProfileUseCase {
    override suspend fun execute(input: GetUserProfileUseCase.Input): GetUserProfileUseCase.Output  =
        withContext(Dispatchers.IO) {
            val result = userProfileRepository.getCurrentUserProfile()
            GetUserProfileUseCase.Output(result)
        }
}