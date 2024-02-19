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
import javax.inject.Inject


/*
    View Model for Register Screen
 */

@HiltViewModel
class RegisterScreenViewModel @Inject constructor(): ViewModel() {
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
    }

    private fun validateFullName(input: String) {
        hasFullNameError = TextUtils.isEmpty(input);
    };


    fun updateEmail(input: String) {
        email = input;
    }
    private fun validateEmail(input: String) {
        val emailPattern = Patterns.EMAIL_ADDRESS;

        hasEmailError = !(!TextUtils.isEmpty(input) && emailPattern.matcher(input).matches())
    };

    fun updatePassword(input: String) {
        password = input;
    }

    private fun validatePassword(input: String) {
        hasPasswordError = input.length < 8
    }
    fun registerUser() {
        println("$fullName + $email + $password")
    }
}