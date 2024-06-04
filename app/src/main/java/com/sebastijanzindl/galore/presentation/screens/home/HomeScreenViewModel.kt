package com.sebastijanzindl.galore.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastijanzindl.galore.domain.usecase.GetUserFlavoursUseCase
import com.sebastijanzindl.galore.domain.usecase.GetUserProfileUseCase
import com.sebastijanzindl.galore.domain.usecase.SignOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.gotrue.Auth
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val getUserFlavoursUseCase: GetUserFlavoursUseCase,
    private val auth: Auth,
): ViewModel(){
     fun logout(navigateToAuth: () -> Unit) {
        viewModelScope.launch {
            val result = signOutUseCase.execute(SignOutUseCase.Input())
            when(result) {
                is SignOutUseCase.Output.Success -> {
                    navigateToAuth()
                }
                else -> {
                    throw Exception("There was an error while logging out!")
                }
            }
        }
    }

    init {
        getUserFlavours()
    }

    private fun getUserFlavours() {
        viewModelScope.launch {
            val user = auth.currentUserOrNull()

            if(user != null) {
                val result = getUserFlavoursUseCase.execute(
                    GetUserFlavoursUseCase.Input(user.id)
                )
                println(result.result);
            }

        }
    }

}