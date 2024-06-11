package com.sebastijanzindl.galore.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
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
import com.sebastijanzindl.galore.presentation.component.MenuItem
import com.sebastijanzindl.galore.presentation.component.ProfileBottomSheet
import com.sebastijanzindl.galore.presentation.component.ProvideSnackbarController
import com.sebastijanzindl.galore.presentation.component.TopAppBar
import com.sebastijanzindl.galore.presentation.viewmodels.MainViewModel
import com.sebastijanzindl.galore.presentation.viewmodels.ProfileSharedViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun GaloreApp(
    viewModel: MainViewModel = hiltViewModel(),
    userProfileViewModel: ProfileSharedViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current

    val hasNotificationPermission by remember {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            mutableStateOf(
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            )
        } else {
          mutableStateOf(true)
        }
    }
    viewModel.updateNotificationPermission(
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
        mutableStateOf(false)
    }

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val userProfile by userProfileViewModel.userProfile.collectAsState()

    val openBottomSheet = {
        showBottomSheet= true
    }

    val dismissBottomSheet = {
        showBottomSheet = false
    }

    val scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val currentRoute = navBackStackEntry?.destination?.route;

    val routeArguments = navBackStackEntry?.arguments

    when(currentRoute) {
        AppScreen.Main.Home.route,
        AppScreen.Main.Search.route,
        AppScreen.Main.Library.route,
        AppScreen.Main.Generate.route -> {
            bottomBarState.value = true
            topBarState.value = true
        }

        AppScreen.Settings.SettingsOverview.route,
        AppScreen.Settings.AccountSettings.route,
        AppScreen.Settings.PasswordAndSecurity.route,
        AppScreen.Settings.NotificationSettings.route,
        AppScreen.Settings.PrivacyPolicy.route,
        AppScreen.Settings.TermsAndConditions.route,
        AppScreen.Main.CocktailSection.route,
        AppScreen.Main.CocktailDetails.route,
        AppScreen.Main.GeneratedCocktailDetails.route, -> {
            topBarState.value = true
            bottomBarState.value = false
        }
        else -> {
            bottomBarState.value = false
            topBarState.value = false
        }
    }
    ProvideSnackbarController(snackbarHostState = snackBarHostState, coroutineScope = coroutineScope) {
        Scaffold (
            modifier = Modifier.fillMaxSize(),
            snackbarHost = {
                SnackbarHost(hostState = snackBarHostState)
            },
            topBar = {
                if(topBarState.value) {
                    TopAppBar(
                        scrollBehaviour = scrollBehaviour,
                        openBottomSheet = openBottomSheet,
                        navigateBack = {
                            navController.popBackStack()
                        },
                        currentRoute = currentRoute,
                        arguments = routeArguments
                    )
                }
            },
            bottomBar = {
                if(bottomBarState.value) {
                    BottomNavigationBar(navController = navController)
                }
            }
        ){ paddingValues ->
            RootNavHost(
                navHostController = navController,
                paddingValues
            )
            /**
             * The Application top bar bottom sheet component
             */
            if(showBottomSheet) {
                ProfileBottomSheet(
                    userProfile = userProfile,
                    sheetState = sheetState,
                    onDismissRequest = dismissBottomSheet,
                    refetchProfile = { userProfileViewModel.fetchUserProfile() },
                    modifier = Modifier
                ) {
                    MenuItem(
                        buttonIcon = ButtonComposableWrapper {  Icon(Icons.Default.Settings, "") },
                        title = "Settings") {
                        coroutineScope.launch {
                            sheetState.hide()
                            showBottomSheet = false
                            navController.navigate(AppScreen.Settings.route)
                        }
                    }
                    MenuItem(buttonIcon = ButtonComposableWrapper {  Icon(painterResource(id = R.drawable.question_mark_24px), "") }, title = "Help") {
                        coroutineScope.launch {
                            sheetState.hide()
                            showBottomSheet = false
                            navController.navigate(AppScreen.Settings.route)
                        }
                    }
                    MenuItem(buttonIcon = ButtonComposableWrapper {  Icon(painterResource(id = R.drawable.logout_24px), "") }, title = "Logout") {
                        coroutineScope.launch {
                            sheetState.hide()
                            showBottomSheet = false
                            userProfileViewModel.logout {
                                navController.navigate(AppScreen.Auth.Welcome.route) {
                                    popUpTo(AppScreen.Main.route) {
                                        inclusive = true
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}

