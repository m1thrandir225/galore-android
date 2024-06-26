package com.sebastijanzindl.galore.presentation.screens.login

import android.text.TextUtils
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastijanzindl.galore.domain.usecase.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
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
    fun loginUser(successCallback: () -> Unit) {
        viewModelScope.launch {
            val result = signInUseCase.execute(
                SignInUseCase.Input(
                    email = email,
                    password = password
                )
            )
            when(result) {
                is SignInUseCase.Output.Success -> {
                    successCallback();
                }
                else -> {
                    throw Exception("there has been a problem while logging you in")
                }
            }
        }
    }
}