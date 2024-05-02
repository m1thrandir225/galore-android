package com.sebastijanzindl.galore.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val supabaseClient: SupabaseClient
): ViewModel() {
    val sessionStatus = supabaseClient.auth.sessionStatus

    var enabledNotifications by mutableStateOf(false)
        private set
    fun setHasEnabledNotifications(value: Boolean) {
        enabledNotifications = value;
    }
}