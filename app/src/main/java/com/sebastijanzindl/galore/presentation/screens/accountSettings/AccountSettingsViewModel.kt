package com.sebastijanzindl.galore.presentation.screens.accountSettings

import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.ViewModel
import com.sebastijanzindl.galore.presentation.component.SnackbarMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class AccountSettingsViewModel @Inject constructor(
) : ViewModel() {
    private val _toastMessage = MutableStateFlow<SnackbarMessage?>(null)
    val toastMessage = _toastMessage.asStateFlow()

    fun sendToastMessage(message: String) {
        val snackbarMessage = SnackbarMessage.from(
            withDismissAction = false,
            userMessage = com.sebastijanzindl.galore.presentation.component.UserMessage.from(message),
            actionLabelMessage = null,
            onSnackbarResult =  {},
            duration = SnackbarDuration.Short

        );
        _toastMessage.update {
            snackbarMessage
        }
    }

    fun dismissToastMessage() {
        _toastMessage.update { null }
    }
}