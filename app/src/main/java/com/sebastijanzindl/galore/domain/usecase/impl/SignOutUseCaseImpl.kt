package com.sebastijanzindl.galore.domain.usecase.impl

import com.sebastijanzindl.galore.data.repository.AuthenticationRepository
import com.sebastijanzindl.galore.domain.usecase.SignOutUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignOutUseCaseImpl @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
): SignOutUseCase{

    override suspend fun execute(input: SignOutUseCase.Input): SignOutUseCase.Output =
        withContext(Dispatchers.IO) {
            val result = authenticationRepository.signOut()
            SignOutUseCase.Output();
        }
}