package com.sebastijanzindl.galore.compose.screens.splash

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val supabaseClient: SupabaseClient
) : ViewModel() {

    val sessionStatus = supabaseClient.auth.sessionStatus
}