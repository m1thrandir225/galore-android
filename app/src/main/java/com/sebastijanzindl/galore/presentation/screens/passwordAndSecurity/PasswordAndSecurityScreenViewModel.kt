package com.sebastijanzindl.galore.presentation.screens.passwordAndSecurity

import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastijanzindl.galore.domain.usecase.DeleteUserUseCase
import com.sebastijanzindl.galore.presentation.component.SnackbarMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.gotrue.Auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PasswordAndSecurityScreenViewModel @Inject constructor(
   // private val updatePasswordUseCase: UpdatePasswordUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val auth: Auth,
) : ViewModel() {
    private val _toastMessage = MutableStateFlow<SnackbarMessage?>(null)
    val toastMessage = _toastMessage.asStateFlow()
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

    fun sendPasswordResetRequest(email: String) {
        viewModelScope.launch {
            auth.resetPasswordForEmail(email = email, );
            sendToastMessage("Password request sent!")
        }
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