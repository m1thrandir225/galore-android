package com.sebastijanzindl.galore.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastijanzindl.galore.domain.models.UserProfile
import com.sebastijanzindl.galore.domain.usecase.GetUserProfileUseCase
import com.sebastijanzindl.galore.domain.usecase.SignOutUseCase
import com.sebastijanzindl.galore.domain.usecase.UpdateUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.user.UserSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileSharedViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val updateUserProfileUseCase: UpdateUserProfileUseCase,
    private val auth: Auth
) : ViewModel() {
    private val _userProfile = MutableStateFlow<UserProfile?>(null)
    private val sessionStatus = auth.currentSessionOrNull();

    private val _isLoading = MutableStateFlow<Boolean>(false);
    val isLoading = _isLoading.asStateFlow();

    val isAuthenticated = when(sessionStatus) {
        is UserSession -> true
        else -> false
    }
    val userProfile = _userProfile.asStateFlow()
    init {
        if(isAuthenticated) {
            fetchUserProfile()
        }
    }

    private fun clearUserProfile() {
        _userProfile.value = null
    }
    fun fetchUserProfile() {
        viewModelScope.launch {
            try {
                _isLoading.value = true;
                val result = getUserProfileUseCase.execute(
                    GetUserProfileUseCase.Input()
                )
                _userProfile.value = result.result
            } catch(e: Exception) {
                Log.e("Exception", e.message ?: "Fetch Profile Exception")
            } finally {
                _isLoading.value = false;
            }
        }
    }
    fun updateProfile(updatedProfile: UserProfile, successCallback: (() -> Unit)?) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = updateUserProfileUseCase.execute(
                    UpdateUserProfileUseCase.Input(
                        updatedProfile
                    )
                )
                _userProfile.value = response.result
                if (successCallback != null) {
                    successCallback()
                }
            } catch (e: Exception) {
                Log.e("Exception", e.message ?: "Update Profile Error")
            } finally {
                _isLoading.value = false;
            }

        }
    }

    fun logout(successCallback: () -> Unit) {
        try {
            clearUserProfile()
            viewModelScope.launch {
                val result = signOutUseCase.execute(SignOutUseCase.Input())
                when(result) {
                    is SignOutUseCase.Output.Success -> {
                        successCallback()
                    }
                    else -> {
                        throw Exception("There was an error logging you out")
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("Exception", e.message?: "Error Logout")
        } finally {
            _isLoading.value = false;
        }

    }
}