package com.sebastijanzindl.galore.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastijanzindl.galore.domain.models.UserProfile
import com.sebastijanzindl.galore.domain.usecase.GetUserProfileUseCase
import com.sebastijanzindl.galore.domain.usecase.SignOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val signOutUseCase: SignOutUseCase,
): ViewModel() {
    private val _userProfile = MutableStateFlow<UserProfile?>(null);

    val userProfile = _userProfile.asStateFlow();

    init {
        getUserProfileData();
    }
    var enabledNotifications by mutableStateOf(false)
        private set

    fun setHasEnabledNotifications(value: Boolean) {
        enabledNotifications = value;
    }

    private fun getUserProfileData() {
        viewModelScope.launch {
            try {
                val result = getUserProfileUseCase.execute(
                    GetUserProfileUseCase.Input()
                );
                _userProfile.value = result.result;
            } catch (e: Exception) {
                //
            }
        }
    }

    fun logout(navigateToAuth: () -> Unit) {
        viewModelScope.launch {
            val result = signOutUseCase.execute(SignOutUseCase.Input())
            when (result) {
                is SignOutUseCase.Output.Success -> {
                    navigateToAuth()
                }

                else -> {
                    throw Exception("There was an error while logging out!")
                }
            }
        }

    }
}