package com.sebastijanzindl.galore.presentation.screens.passwordAndSecurity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PasswordAndSecurityScreenViewModel @Inject constructor(
   // private val updatePasswordUseCase: UpdatePasswordUseCase,
    //private val deleteAccountUseCase: DeleteAccountUseCase,
) : ViewModel() {
    var currentPassword by mutableStateOf("")
        private set
    var newPassword by mutableStateOf("")
        private set
    var confirmNewPassword by mutableStateOf("")
        private set

    var updatePasswordButtonEnabled by mutableStateOf(false)
        private set

    fun updateCurrentPassword(value: String) {
        currentPassword = value
    }

    fun updateNewPassword(value: String) {
        newPassword = value
    }

    fun confirmNewPassword(value: String) {
        confirmNewPassword = value
    }
}