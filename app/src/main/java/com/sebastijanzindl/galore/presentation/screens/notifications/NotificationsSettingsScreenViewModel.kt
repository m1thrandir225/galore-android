package com.sebastijanzindl.galore.presentation.screens.notifications

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastijanzindl.galore.domain.usecase.GetDeviceFCMTokenUseCase
import com.sebastijanzindl.galore.domain.usecase.RegisterFCMTokenUseCase
import com.sebastijanzindl.galore.presentation.component.SnackbarMessage
import com.sebastijanzindl.galore.presentation.component.UserMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.gotrue.Auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationsSettingsScreenViewModel @Inject constructor(
    private val getDeviceFCMTokenUseCase: GetDeviceFCMTokenUseCase,
    private val registerFCMTokenUseCase: RegisterFCMTokenUseCase,
    private val auth: Auth
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _toastMessage = MutableStateFlow<SnackbarMessage?>(null)
    val toastMessage = _toastMessage.asStateFlow()

    fun dismissToast() {
        _toastMessage.update { null }
    }
    fun uploadFCMToken(deviceId: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true

                val user = auth.currentUserOrNull() ?: throw Exception("Unable to get current user.")

                val deviceFCMToken = getDeviceFCMTokenUseCase.execute(
                    GetDeviceFCMTokenUseCase.Input()
                )
                if(deviceFCMToken.result == null) throw Exception("There was a problem getting the device token.")


                val registerFCMResponse = registerFCMTokenUseCase.execute(
                    RegisterFCMTokenUseCase.Input(token = deviceFCMToken.result, userId = user.id, deviceId)
                )



                when(registerFCMResponse) {
                    RegisterFCMTokenUseCase.Output.Success -> {
                        _toastMessage.value = SnackbarMessage.from(
                            duration = SnackbarDuration.Short,
                            userMessage = UserMessage.from("Successfully updated settings"),
                            actionLabelMessage = null,
                            onSnackbarResult = {},
                            withDismissAction = false
                        )
                    }

                    RegisterFCMTokenUseCase.Output.AlreadyExists -> {

                    }
                    RegisterFCMTokenUseCase.Output.Failure -> {
                        throw Exception("There was an error while uploading the FCM token")
                    }
                }

            } catch (e: Exception) {
                Log.e("Possible exception", e.message.toString())
                _toastMessage.value = SnackbarMessage.from(
                    duration = SnackbarDuration.Short,
                    userMessage = UserMessage.from("An error occurred."),
                    actionLabelMessage = null,
                    onSnackbarResult = {},
                    withDismissAction = false
                )
            } finally {
                _isLoading.value = false
            }



        }
    }
}