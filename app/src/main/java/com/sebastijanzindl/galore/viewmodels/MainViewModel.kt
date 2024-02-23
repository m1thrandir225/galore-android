package com.sebastijanzindl.galore.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {
    var isLoggedIn by mutableStateOf(false)
        private set

    fun setIsLoggedIn(value: Boolean) {
        isLoggedIn = value;
    }
}