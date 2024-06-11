package com.sebastijanzindl.galore.domain.usecase.impl

import com.sebastijanzindl.galore.data.repository.AuthenticationRepository
import com.sebastijanzindl.galore.domain.usecase.SignUpUseCase
import com.sebastijanzindl.galore.presentation.util.EncodeURL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignUpUseCaseImpl @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : SignUpUseCase {
    override suspend fun execute(input: SignUpUseCase.Input): SignUpUseCase.Output =
        withContext(Dispatchers.IO) {
            val result = authenticationRepository.signUp(
                userEmail = input.email,
                userPassword = input.password,
                fullName = input.fullName,


                avatarUrl = "https://source.boringavatars.com/beam/120/${EncodeURL(input.fullName)}?colors=30182B,F0F1BC,60F0C0,FF360E,191F04"
            )
            if(result) {
                SignUpUseCase.Output.Success
            } else {
                SignUpUseCase.Output.Failure
            }
        }
}