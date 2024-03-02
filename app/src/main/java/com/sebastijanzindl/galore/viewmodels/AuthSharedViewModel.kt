package com.sebastijanzindl.galore.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastijanzindl.galore.domain.usecase.SignInGoogleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthSharedViewModel @Inject constructor(
    private val signInGoogleUseCase: SignInGoogleUseCase
): ViewModel() {
    fun signInWithGoogle(token: String, rawNonce: String) {
        viewModelScope.launch {
            val result = signInGoogleUseCase.execute(
                SignInGoogleUseCase.Input(
                    token = token,
                    rawNonce = rawNonce
                )
            )
        }
    }
}