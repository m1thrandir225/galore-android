package com.sebastijanzindl.galore.presentation.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sebastijanzindl.galore.R
import com.sebastijanzindl.galore.presentation.component.ButtonComposableWrapper
import com.sebastijanzindl.galore.presentation.component.Logo
import com.sebastijanzindl.galore.presentation.component.MenuItem
import com.sebastijanzindl.galore.ui.theme.GaloreTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsOverviewScreen(
    modifier: Modifier = Modifier,
    navigateToAccountSettings: () -> Unit,
    navigateToPasswordsAndSecurity: () -> Unit,
    navigateToNotifications: () -> Unit,
) {
    Column (
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = 10.dp,
                end = 10.dp
            )
    ) {
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(48.dp)
        ) {
            Column (
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                MenuItem(buttonIcon = ButtonComposableWrapper {
                    Icon(painter = painterResource(id = R.drawable.manage_accounts_24px), contentDescription = "Account Settings")
                },
                    title = "Account Settings",
                    action = navigateToAccountSettings
                )
                MenuItem(buttonIcon = ButtonComposableWrapper {
                    Icon(painter = painterResource(id = R.drawable.key_24px), contentDescription = "Password & Security")
                },
                    title = "Password & Security",
                    action = navigateToPasswordsAndSecurity
                )
                MenuItem(buttonIcon =
                    ButtonComposableWrapper {
                        Icon(painter = painterResource(id = R.drawable.notifications_24px), contentDescription = "Notifications")
                    },
                    title = "Notifications",
                    action = navigateToNotifications
                )
            }
            Column (
                modifier = Modifier.padding(horizontal = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                MenuItem(buttonIcon = null, title = "Terms & Conditions") {
                    println("hello world")
                }
                MenuItem(buttonIcon = null, title = "Privacy Policy") {
                    println("hello world")
                }
            }
        }


        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Logo()
            //TODO: refactor the app version stuff
            System.getProperty("jpackage.app-version")?.let { Text(text = it) }
        }
    }
}

@Preview
@Composable
private fun SettingsScreenPreview() {
    GaloreTheme {
        SettingsOverviewScreen(
            navigateToAccountSettings = {},
            navigateToNotifications = {},
            navigateToPasswordsAndSecurity = {},
        )
    }
}