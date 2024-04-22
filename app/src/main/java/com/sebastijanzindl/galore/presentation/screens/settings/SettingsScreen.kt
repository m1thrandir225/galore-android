package com.sebastijanzindl.galore.presentation.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sebastijanzindl.galore.R
import com.sebastijanzindl.galore.presentation.component.MenuItem
import com.sebastijanzindl.galore.ui.theme.GaloreTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    navigateToAccountSettings: () -> Unit,
    navigateToPasswordsAndSecurity: () -> Unit,
    navigateToNotifications: () -> Unit,
) {
    val scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold (
        modifier = modifier,
        topBar = { SettingsTopBar(scrollBehavior = scrollBehaviour, navigateBack = navigateBack) }
    ) {contentPadding ->
        Column (
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(top = contentPadding.calculateTopPadding(), bottom = contentPadding.calculateBottomPadding(), start = 10.dp, end = 10.dp)
        ) {
            MenuItem(buttonIcon = {
                Icon(painter = painterResource(id = R.drawable.manage_accounts_24px), contentDescription = "Account Settings")
            },
                title = "Account Settings",
                action = navigateToAccountSettings
            )
            MenuItem(buttonIcon = {
                Icon(painter = painterResource(id = R.drawable.key_24px), contentDescription = "Password & Security")
            },
                title = "Password & Security",
                action = navigateToPasswordsAndSecurity
            )
            MenuItem(buttonIcon = {
                Icon(painter = painterResource(id = R.drawable.notifications_24px), contentDescription = "Notifications")
            },
                title = "Notifications",
                action = navigateToNotifications
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsTopBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
    navigateBack: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        title = {
        Text(text = "Settings")
    },
        navigationIcon = {
                     IconButton(onClick = navigateBack) {
                         Icon(
                             imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                             contentDescription = "Go Back Icon"
                         )
                     }
        },
        scrollBehavior = scrollBehavior
    )
}

@Preview
@Composable
private fun SettingsScreenPreview() {
    GaloreTheme {
        SettingsScreen(
            navigateBack = {},
            navigateToAccountSettings = {},
            navigateToNotifications = {},
            navigateToPasswordsAndSecurity = {},
        )
    }
}