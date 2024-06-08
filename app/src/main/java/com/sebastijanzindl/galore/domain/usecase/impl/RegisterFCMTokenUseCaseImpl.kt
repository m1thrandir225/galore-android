package com.sebastijanzindl.galore.domain.usecase.impl

import com.sebastijanzindl.galore.data.repository.NotificationRepository
import com.sebastijanzindl.galore.domain.usecase.RegisterFCMTokenUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegisterFCMTokenUseCaseImpl @Inject constructor(
    private val notificationRepository: NotificationRepository
) : RegisterFCMTokenUseCase {
    override suspend fun execute(input: RegisterFCMTokenUseCase.Input): RegisterFCMTokenUseCase.Output =
        withContext(Dispatchers.IO) {
            val response = notificationRepository.registerFCMToken(
                token = input.token,
                userId = input.userId
            )

            if(response != null) {
                RegisterFCMTokenUseCase.Output.Success
            } else {
                RegisterFCMTokenUseCase.Output.Failure
            }
        }
}