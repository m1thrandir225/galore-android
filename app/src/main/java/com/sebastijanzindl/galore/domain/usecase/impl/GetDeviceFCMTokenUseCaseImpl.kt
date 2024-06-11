package com.sebastijanzindl.galore.domain.usecase.impl

import com.google.firebase.messaging.FirebaseMessaging
import com.sebastijanzindl.galore.domain.usecase.GetDeviceFCMTokenUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetDeviceFCMTokenUseCaseImpl @Inject constructor(
    private val firebaseMessaging: FirebaseMessaging
) : GetDeviceFCMTokenUseCase{

    override suspend fun execute(input: GetDeviceFCMTokenUseCase.Input): GetDeviceFCMTokenUseCase.Output =
        withContext(Dispatchers.IO) {
            val response = firebaseMessaging.token.await();

            if(response != null) {
                GetDeviceFCMTokenUseCase.Output.Success(response)
            } else {
                GetDeviceFCMTokenUseCase.Output.Failure()
            }
        }
}