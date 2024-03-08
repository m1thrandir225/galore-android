package com.sebastijanzindl.galore.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastijanzindl.galore.domain.usecase.SignOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase
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
}