package com.sebastijanzindl.galore.presentation.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastijanzindl.galore.domain.models.UserProfile
import com.sebastijanzindl.galore.domain.usecase.GetUserProfileUseCase
import com.sebastijanzindl.galore.domain.usecase.SignOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase,
): ViewModel(){

    private val _uiState = MutableStateFlow(HomeScreenUiState(null));

    val uiState: StateFlow<HomeScreenUiState> = _uiState.asStateFlow();

    init {
        viewModelScope.launch {
            val result = getUserProfileUseCase.execute(
                GetUserProfileUseCase.Input()
            );
            when(result.result) {
                is UserProfile -> {
                    _uiState.value = HomeScreenUiState(result.result)
                } else -> {
                }
            }

        }
    }

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

data class HomeScreenUiState(
    val userProfile: UserProfile?
)