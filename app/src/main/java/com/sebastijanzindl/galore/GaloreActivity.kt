package com.sebastijanzindl.galore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sebastijanzindl.galore.presentation.GaloreApp
import com.sebastijanzindl.galore.ui.theme.GaloreTheme
import dagger.hilt.android.AndroidEntryPoint
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.handleDeeplinks
import javax.inject.Inject

@AndroidEntryPoint
class GaloreActivity: ComponentActivity() {
    @Inject lateinit var supabaseClient: SupabaseClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supabaseClient.handleDeeplinks(intent)

        //enableEdgeToEdge()
        setContent {
            GaloreTheme {
                GaloreApp()
            }
        }
    }
}