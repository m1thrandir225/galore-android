package com.sebastijanzindl.galore.domain.usecase.impl

import com.sebastijanzindl.galore.domain.usecase.GetAllUserMadeCocktails
import com.sebastijanzindl.galore.domain.usecase.LibraryScreenUseCase
import io.github.jan.supabase.gotrue.Auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LibraryScreenUseCaseImpl  @Inject constructor(
    private val authRepository: Auth,
    private val userMadeCocktails: GetAllUserMadeCocktails
): LibraryScreenUseCase {
    override suspend fun execute(input: LibraryScreenUseCase.Input): LibraryScreenUseCase.Output =
        withContext(Dispatchers.IO) {
            authRepository.awaitInitialization();
            val user = authRepository.currentUserOrNull();
            if(user == null) {
                LibraryScreenUseCase.Output(null);
            }

            val cocktails = userMadeCocktails.execute(
                GetAllUserMadeCocktails.Input(
                    userId = user!!.id
                )
            )
            LibraryScreenUseCase.Output(cocktails.result)
        }
}