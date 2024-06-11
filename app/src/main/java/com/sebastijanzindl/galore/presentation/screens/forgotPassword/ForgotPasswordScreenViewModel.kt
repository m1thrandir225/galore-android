package com.sebastijanzindl.galore.presentation.screens.forgotPassword

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastijanzindl.galore.presentation.component.SnackbarMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.gotrue.Auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordScreenViewModel @Inject constructor(
    private val auth: Auth,
) : ViewModel() {
    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading = _isLoading.asStateFlow()


    private val _toastMessage = MutableStateFlow<SnackbarMessage?>(null)
    val toastMessage = _toastMessage.asStateFlow()

    private val  _magicLinkSent= MutableStateFlow<Boolean?>(null)
    val magicLinkSent = _magicLinkSent.asStateFlow()
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

    fun dismissToast() {
        _toastMessage.update { null }
    }
    fun sendPasswordResetRequest(email: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                auth.resetPasswordForEmail(email)
                sendToastMessage("Please check your email")
                _magicLinkSent.value = true
            } catch (e: Exception) {
                e.message?.let { Log.e("Password Reset Request", it) }
                _magicLinkSent.value = false
                sendToastMessage("An error occurred")
            } finally {
                _isLoading.value = false
            }
        }
    }
}