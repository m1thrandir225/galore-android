package com.sebastijanzindl.galore.compose.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastijanzindl.galore.domain.usecase.SignOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase
): ViewModel(){

    fun logout() {
        viewModelScope.launch {
            signOutUseCase.execute(SignOutUseCase.Input())
        }

    }
}