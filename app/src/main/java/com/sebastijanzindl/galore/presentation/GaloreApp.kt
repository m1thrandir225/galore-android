package com.sebastijanzindl.galore.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sebastijanzindl.galore.R
import com.sebastijanzindl.galore.navigation.AppScreen
import com.sebastijanzindl.galore.navigation.RootNavHost
import com.sebastijanzindl.galore.presentation.component.BottomNavigationBar
import com.sebastijanzindl.galore.presentation.component.ButtonComposableWrapper
import com.sebastijanzindl.galore.presentation.component.HomeTopAppBar
import com.sebastijanzindl.galore.presentation.component.MenuItem
import com.sebastijanzindl.galore.presentation.component.ProfileBottomSheet
import com.sebastijanzindl.galore.presentation.viewmodels.MainViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun GaloreApp(
    viewModel: MainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val coroutineScope = rememberCoroutineScope()

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

    val snackBarHostState = remember {
        SnackbarHostState()
    }
    val bottomBarState = rememberSaveable {
        mutableStateOf(true)
    }
    val topBarState = rememberSaveable {
        mutableStateOf(true)
    }

    var showBottomSheet by remember {
        mutableStateOf(false);
    }

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true);

    val userProfile by viewModel.userProfile.collectAsState()

    val openBottomSheet = {
        showBottomSheet= true;
    }

    val dismissBottomSheet = {
        showBottomSheet = false
    }

    val scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior()


    when(navBackStackEntry?.destination?.route) {
        AppScreen.Main.Home.route -> {
            bottomBarState.value = true
            topBarState.value = true
        }
        AppScreen.Main.Search.route -> {
            bottomBarState.value = true
            topBarState.value = true
        }

        AppScreen.Main.Library.route -> {
            bottomBarState.value = true
            topBarState.value = true
        }
        AppScreen.Main.Generate.route -> {
            bottomBarState.value = true
            topBarState.value = true
        }

        else -> {
            bottomBarState.value = false
            topBarState.value = false
        }
    }

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
                       SnackbarHost(hostState = snackBarHostState)
        },
        topBar = {
            if(topBarState.value) {
                HomeTopAppBar(scrollBehaviour = scrollBehaviour, openBottomSheet = openBottomSheet)
            }
        },
        bottomBar = {
            if(bottomBarState.value) {
                BottomNavigationBar(navController = navController)
            }
        }
    ){paddingValues ->
        RootNavHost(
            navHostController = navController,
            paddingValues
        )
        if(showBottomSheet) {
            ProfileBottomSheet(
                userProfile = userProfile,
                sheetState = sheetState,
                onDismissRequest = dismissBottomSheet,
                modifier = Modifier
            ) {
                MenuItem(
                    buttonIcon = ButtonComposableWrapper {  Icon(Icons.Default.Settings, "") },
                    title = "Settings") {
                    coroutineScope.launch {
                        sheetState.hide()
                    }
                }
                MenuItem(buttonIcon = ButtonComposableWrapper {  Icon(painterResource(id = R.drawable.question_mark_24px), "") }, title = "Help") {
                    coroutineScope.launch {
                        sheetState.hide()
                    }

                }
                MenuItem(buttonIcon = ButtonComposableWrapper {  Icon(painterResource(id = R.drawable.logout_24px), "") }, title = "Logout") {
                    coroutineScope.launch {
                        sheetState.hide();
                    }
                    viewModel.logout {
                        navController.navigate(AppScreen.Auth.Welcome.route) {
                            popUpTo(AppScreen.Main.route) {
                                inclusive = true;
                            }
                        }
                    };
                }
            }
        }
    }
}

