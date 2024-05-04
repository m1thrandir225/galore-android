package com.sebastijanzindl.galore.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastijanzindl.galore.domain.usecase.SignInGoogleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.compose.auth.ComposeAuth
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthSharedViewModel @Inject constructor(
    private val signInGoogleUseCase: SignInGoogleUseCase,
    private val composeAuth: ComposeAuth,
): ViewModel() {
    fun signInWithGoogle(token: String, rawNonce: String) {
        viewModelScope.launch {
            signInGoogleUseCase.execute(
                SignInGoogleUseCase.Input(
                    token = token,
                    rawNonce = rawNonce
                )
            )
        }
    }
    val supabaseComposeAuth = composeAuth;
}