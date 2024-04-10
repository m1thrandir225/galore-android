package com.sebastijanzindl.galore.domain.usecase.impl

import com.sebastijanzindl.galore.data.repository.AuthenticationRepository
import com.sebastijanzindl.galore.data.repository.UserProfileRepository
import com.sebastijanzindl.galore.domain.models.UserProfile
import com.sebastijanzindl.galore.domain.usecase.GetUserProfileUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserProfileUseCaseImpl @Inject constructor(
    private val userProfileRepository: UserProfileRepository,
    private val authenticationRepository: AuthenticationRepository
) : GetUserProfileUseCase {
    override suspend fun execute(input: GetUserProfileUseCase.Input): GetUserProfileUseCase.Output  =
        withContext(Dispatchers.IO) {
            val userId = authenticationRepository.getLoggedInUserID();

            if(userId.isNotEmpty()) {
                val result = userProfileRepository.getCurrentUserProfile(userId)
                GetUserProfileUseCase.Output(result)
            } else {
                GetUserProfileUseCase.Output(result =  null)
            }
        }
}