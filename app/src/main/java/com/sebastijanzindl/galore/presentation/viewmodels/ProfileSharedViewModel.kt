package com.sebastijanzindl.galore.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastijanzindl.galore.domain.models.UserProfile
import com.sebastijanzindl.galore.domain.usecase.GetUserProfileUseCase
import com.sebastijanzindl.galore.domain.usecase.SignOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.SessionStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileSharedViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val auth: Auth
) : ViewModel() {
    private val _userProfile = MutableStateFlow<UserProfile?>(null)
    private val sessionStatus = auth.sessionStatus;

    val isAuthenticated = when(sessionStatus.value) {
        is SessionStatus.Authenticated -> true
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
                val result = getUserProfileUseCase.execute(
                    GetUserProfileUseCase.Input()
                )
                _userProfile.value = result.result
            } catch(e: Exception) {
                //
            }
        }
    }

    fun logout(successCallback: () -> Unit) {
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


    }
}