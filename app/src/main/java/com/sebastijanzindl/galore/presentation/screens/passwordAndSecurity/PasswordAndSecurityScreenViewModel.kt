package com.sebastijanzindl.galore.presentation.screens.passwordAndSecurity

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastijanzindl.galore.domain.usecase.DeleteUserUseCase
import com.sebastijanzindl.galore.presentation.component.SnackbarMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PasswordAndSecurityScreenViewModel @Inject constructor(
   // private val updatePasswordUseCase: UpdatePasswordUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
) : ViewModel() {
    private val _toastMessage = MutableStateFlow<SnackbarMessage?>(null)
    val toastMessage = _toastMessage.asStateFlow()
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
        val snackbarMessage = SnackbarMessage.from(
            withDismissAction = false,
            userMessage = com.sebastijanzindl.galore.presentation.component.UserMessage.from(message),
            actionLabelMessage = null,
            onSnackbarResult = {},
            duration = SnackbarDuration.Short
        )
        _toastMessage.update {
            snackbarMessage
        }
    }

    fun dismissToastMessage() {
        _toastMessage.update { null }
    }

    fun deleteAccount(successCallback: () -> Unit) {
        viewModelScope.launch {
           val result =  deleteUserUseCase.execute(
                DeleteUserUseCase.Input()
            )
            when(result) {
                is DeleteUserUseCase.Output.Success -> {
                    sendToastMessage("Your account was successfully deleted!")
                    successCallback()
                }
                else -> {
                    sendToastMessage("There was an error deleting your account");
                }
            }
        }
    }
}