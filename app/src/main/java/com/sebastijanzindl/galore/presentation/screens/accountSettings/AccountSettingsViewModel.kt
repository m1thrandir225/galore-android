package com.sebastijanzindl.galore.presentation.screens.accountSettings

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastijanzindl.galore.domain.models.UserProfile
import com.sebastijanzindl.galore.domain.usecase.GetUserProfileUseCase
import com.sebastijanzindl.galore.domain.usecase.UpdateUserProfileUseCase
import com.sebastijanzindl.galore.presentation.component.SnackbarMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.time.ZoneId
import javax.inject.Inject


@HiltViewModel
class AccountSettingsViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val updateUserProfileUseCase: UpdateUserProfileUseCase
) : ViewModel() {
    private val _userProfile = MutableStateFlow<UserProfile?>(null)
    val userProfile = _userProfile.asStateFlow()

    var isLoading by mutableStateOf(false)
    var updateButtonEnabled by mutableStateOf(false)
        private set

    private val _toastMessage = MutableStateFlow<SnackbarMessage?>(null)
    val toastMessage = _toastMessage.asStateFlow()

    var email by mutableStateOf( "")
        private set
    var fullName by mutableStateOf("")
        private set
    var birthday by mutableStateOf<String?>(null )
        private set

    init {
        getProfile()
    }
    private fun sendToastMessage(message: String) {
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


    fun updateProfile() {
        val updatedProfile = _userProfile.value?.copy(fullName = fullName, email =  email)
            ?: throw Exception("error while upading profile")
        viewModelScope.launch {
            val response = updateUserProfileUseCase.execute(
                UpdateUserProfileUseCase.Input(
                    updatedProfile
                )
            )
            _userProfile.value = response.result
            sendToastMessage("Successfully updated profile!")
            updateButtonEnabled = false
        }
    }

    private fun getProfile() {
        viewModelScope.launch {
            try {
                isLoading = true
                val result = getUserProfileUseCase.execute(
                    GetUserProfileUseCase.Input()
                )
                _userProfile.value = result.result

                email = result.result?.email ?: ""
                fullName = result.result?.fullName ?: ""

            } catch(e: Exception) {
                //
            } finally {
                isLoading = false
            }
        }
    }

    fun updateEmail(newEmail: String) {
        updateButtonEnabled = userProfile.value?.email != newEmail
        email = newEmail

    }
    fun updateFullName(newName: String) {
        updateButtonEnabled = userProfile.value?.fullName != newName
        fullName = newName
    }


    fun updateBirthday (milis: Long) {
        birthday = Instant.fromEpochMilliseconds(milis)
            .toLocalDateTime(TimeZone.of(ZoneId.systemDefault().toString())).date.toString()

    }
}