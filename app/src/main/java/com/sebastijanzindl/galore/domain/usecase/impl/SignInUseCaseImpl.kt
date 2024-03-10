package com.sebastijanzindl.galore.domain.usecase.impl

import com.sebastijanzindl.galore.data.repository.AuthenticationRepository
import com.sebastijanzindl.galore.domain.usecase.SignInUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignInUseCaseImpl @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
): SignInUseCase{
    override suspend fun execute(input: SignInUseCase.Input): SignInUseCase.Output  =
        withContext(Dispatchers.IO) {
            val result = authenticationRepository.signIn(input.email, input.password);
            if(result) {
                SignInUseCase.Output.Success
            } else {
                SignInUseCase.Output.Failure
            }
        }
}