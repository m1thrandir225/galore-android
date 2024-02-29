package com.sebastijanzindl.galore.domain.usecase.impl

import com.sebastijanzindl.galore.data.repository.AuthenticationRepository
import com.sebastijanzindl.galore.domain.usecase.SignInGoogleUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignInGoogleUseCaseImpl @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : SignInGoogleUseCase{
    override suspend fun execute(input: SignInGoogleUseCase.Input): SignInGoogleUseCase.Output =
        withContext(Dispatchers.IO) {
            val result = authenticationRepository.signInWithGoogle(token = input.token, rawNonce =  input.rawNonce)
            SignInGoogleUseCase.Output()
        }
}