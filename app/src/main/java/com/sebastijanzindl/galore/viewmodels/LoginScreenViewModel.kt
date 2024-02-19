package com.sebastijanzindl.galore.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


data class LoginScreenUIState(
    var email: String = "",
    var password: String = "",
)
class LoginScreenViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(LoginScreenUIState())

    val uiState: StateFlow<LoginScreenUIState> = _uiState.asStateFlow()


    fun loginUser() {
        println("${uiState.value.email} - ${uiState.value.password}")
    }
}