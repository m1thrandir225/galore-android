package com.sebastijanzindl.galore.presentation.screens.accountSettings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastijanzindl.galore.domain.models.UserProfile
import com.sebastijanzindl.galore.domain.usecase.GetUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.time.ZoneId
import javax.inject.Inject


@HiltViewModel
class AccountSettingsViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase
) : ViewModel() {
    private val _userProfile = MutableStateFlow<UserProfile?>(null)
    val userProfile = _userProfile.asStateFlow()

    var isLoading by mutableStateOf(false)
    init {
        getProfile()
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
        _userProfile.value = userProfile.value?.copy(email = newEmail)
    }
    fun updateFullName(newName: String) {
        _userProfile.value = userProfile.value?.copy(fullName = newName)
    }

    var email by mutableStateOf( "")
        private set
    var fullName by mutableStateOf("")
        private set
    var birthday by mutableStateOf<String?>(null )
        private set

    fun updateBirthday ( milis: Long) {
        birthday = Instant.fromEpochMilliseconds(milis)
            .toLocalDateTime(TimeZone.of(ZoneId.systemDefault().toString())).date.toString()

    }
}