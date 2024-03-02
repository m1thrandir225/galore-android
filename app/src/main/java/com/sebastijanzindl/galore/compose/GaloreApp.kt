package com.sebastijanzindl.galore.compose

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sebastijanzindl.galore.compose.navigation.GaloreNavHost
import com.sebastijanzindl.galore.compose.navigation.authNavGraph
import com.sebastijanzindl.galore.compose.navigation.mainNavGraph
import com.sebastijanzindl.galore.compose.navigation.splashNavGraph
import com.sebastijanzindl.galore.viewmodels.MainViewModel

@Composable
fun GaloreApp(
    viewModel: MainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState();
    val currentRoute = navBackStackEntry?.destination?.route

    val corutineScrope = rememberCoroutineScope();

    val context = LocalContext.current

    val hasNotificationPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    viewModel.setHasEnabledNotifications(
        hasNotificationPermission
    )

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    GaloreNavHost(
        isLoggedIn = viewModel.isLoggedIn,
        navController = navController
    )
}

