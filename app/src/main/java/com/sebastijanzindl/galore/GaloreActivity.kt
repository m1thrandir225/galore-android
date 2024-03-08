package com.sebastijanzindl.galore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sebastijanzindl.galore.presentation.GaloreApp
import com.sebastijanzindl.galore.ui.theme.GaloreTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GaloreActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GaloreTheme {
                GaloreApp()
            }
        }

    }
}