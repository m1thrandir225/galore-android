package com.sebastijanzindl.galore.presentation.screens.passwordAndSecurity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastijanzindl.galore.domain.usecase.DeleteUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PasswordAndSecurityScreenViewModel @Inject constructor(
   // private val updatePasswordUseCase: UpdatePasswordUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
) : ViewModel() {

    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()
    var currentPassword by mutableStateOf("")
        private set
    var newPassword by mutableStateOf("")
        private set
    var confirmNewPassword by mutableStateOf("")
        private set

    var updatePasswordButtonEnabled by mutableStateOf(false)
        private set

    fun updatePasswordButtonState(value: Boolean) {
        updatePasswordButtonEnabled = value
    }

    fun updateCurrentPassword(value: String) {
        currentPassword = value
    }

    fun updateNewPassword(value: String) {
        newPassword = value
    }

    fun updateConfirmNewPassword(value: String) {
        confirmNewPassword = value
    }

    private fun sendToastMessage(message: String) {
        viewModelScope.launch {
            _toastMessage.emit(message)
        }
    }

    fun deleteAccount(successCallback: () -> Unit) {
        viewModelScope.launch {
           val result =  deleteUserUseCase.execute(
                DeleteUserUseCase.Input()
            )
            when(result) {
                is DeleteUserUseCase.Output.Success -> {
                    successCallback()
                }
                else -> {
                    sendToastMessage("There was an error deleting your account");
                }
            }
        }
    }
}