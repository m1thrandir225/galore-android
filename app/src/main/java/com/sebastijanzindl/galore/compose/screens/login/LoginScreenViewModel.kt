package com.sebastijanzindl.galore.compose.screens.login

import android.text.TextUtils
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastijanzindl.galore.domain.usecase.SignInGoogleUseCase
import com.sebastijanzindl.galore.domain.usecase.SignInUseCase
import com.sebastijanzindl.galore.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val signInGoogleUseCase: SignInGoogleUseCase,
    private val signInUseCase: SignInUseCase
) : ViewModel() {
    var email by mutableStateOf("")
        private set

    var hasEmailError by mutableStateOf(false)
        private set

    var password by mutableStateOf("")
        private set

    var hasPasswordError by mutableStateOf(false)
        private set

    fun updateEmail(input: String) {
        email = input
        validateEmail(input)
    }
    private fun validateEmail(input: String) {
        val emailPattern = Patterns.EMAIL_ADDRESS;
        hasEmailError = !(!TextUtils.isEmpty(input) && emailPattern.matcher(input).matches())// Returns true if no error
    }

    fun updatePassword(input: String) {
        password = input
        validatePassword(input);
    }

    private fun validatePassword(input: String) {
        hasPasswordError = input.length < 8;
    }
    fun loginUser(navigateToHome: () -> Unit) {
        viewModelScope.launch {
            val result = signInUseCase.execute(
                SignInUseCase.Input(
                    email = email,
                    password = password
                )
            )
            when(result) {
                is SignInUseCase.Output.Success -> {
                    navigateToHome();
                }
                else -> {
                    throw Exception("there has been a problem while logging you in")
                }
            }
        }
    }

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