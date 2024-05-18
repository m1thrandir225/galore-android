package com.sebastijanzindl.galore.domain.usecase.impl

import com.sebastijanzindl.galore.data.repository.UserProfileRepository
import com.sebastijanzindl.galore.domain.usecase.DeleteUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteUserUseCaseImpl @Inject constructor(
    private val userProfileRepository: UserProfileRepository
) : DeleteUserUseCase {
    override suspend fun execute(input: DeleteUserUseCase.Input): DeleteUserUseCase.Output =
        withContext(Dispatchers.IO) {
            val result = userProfileRepository.deleteUserProfile();
            if(result) {
                DeleteUserUseCase.Output.Success
            } else {
                DeleteUserUseCase.Output.Failure
            }
        }
}