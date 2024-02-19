package com.sebastijanzindl.galore.viewmodels

import android.text.TextUtils
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor() : ViewModel() {
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
    fun loginUser() {
        println("$email + $password")
    }
}