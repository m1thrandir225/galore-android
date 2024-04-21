package com.sebastijanzindl.galore.presentation.screens.register

import android.text.TextUtils
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastijanzindl.galore.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/*
    View Model for Register Screen
 */

@HiltViewModel
class RegisterScreenViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
): ViewModel() {
    var fullName by mutableStateOf("")
        private set
    var hasFullNameError by mutableStateOf(false)
        private set

    var email by mutableStateOf("")
        private set

    var hasEmailError by mutableStateOf(false)
        private set

    var password by mutableStateOf("")
        private set

    var hasPasswordError by mutableStateOf(false)
        private set

    fun updateFullName(input: String) {
        fullName = input;
        validateFullName(input);
    }

    private fun validateFullName(input: String) {
        hasFullNameError = TextUtils.isEmpty(input);
    };

    fun updateEmail(input: String) {
        email = input;
        validateEmail(input);
    }
    private fun validateEmail(input: String) {
        val emailPattern = Patterns.EMAIL_ADDRESS;

        hasEmailError = !(!TextUtils.isEmpty(input) && emailPattern.matcher(input).matches())
    };

    fun updatePassword(input: String) {
        password = input;
        validatePassword(input);
    }

    private fun validatePassword(input: String) {
        hasPasswordError = input.length < 8
    }
    fun registerUser(navigateToOnboarding: () -> Unit) {
        viewModelScope.launch {
            val result = signUpUseCase.execute(
                SignUpUseCase.Input(
                    email = email,
                    password = password,
                    fullName = fullName,
                )
            )
            when(result) {
                is SignUpUseCase.Output.Success -> {
                    navigateToOnboarding();
                }
                else -> {
                    println("Something went wrong")
                }
            }
        }
    }
}