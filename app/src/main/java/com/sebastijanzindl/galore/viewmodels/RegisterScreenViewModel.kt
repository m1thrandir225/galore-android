package com.sebastijanzindl.galore.viewmodels

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


@HiltViewModel
class RegisterScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
): ViewModel() {
    var fullName by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    fun updateFullName(input: String) {
        fullName = input;
    }

    fun updateEmail(input: String) {
        email = input;
    }

    fun updatePassword(input: String) {
        password = input;
    }
    fun registerUser() {
        println("$fullName + $email + $password")
    }
}