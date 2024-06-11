package com.sebastijanzindl.galore.presentation.screens.notifications

import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.sebastijanzindl.galore.presentation.component.SnackbarMessageHandler
import com.sebastijanzindl.galore.presentation.util.deviceId
import com.sebastijanzindl.galore.presentation.viewmodels.ProfileSharedViewModel
import com.sebastijanzindl.galore.ui.theme.GaloreTheme


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NotificationSettings(
    modifier: Modifier  = Modifier,
    profileSharedViewModel: ProfileSharedViewModel = hiltViewModel(),
    viewModel: NotificationsSettingsScreenViewModel = hiltViewModel()
) {
    val profile by profileSharedViewModel.userProfile.collectAsState();
    val isLoading by viewModel.isLoading.collectAsState()
    val toastMessage by viewModel.toastMessage.collectAsState()

    val context = LocalContext.current

    fun updatePushNotifications (value: Boolean) {
        val updatedProfile = profile?.copy(
            pushNotifications = value
        )
        if(updatedProfile != null) {
            profileSharedViewModel.updateProfile(
                updatedProfile,
                successCallback = {
                    if(value) {
                        viewModel.uploadFCMToken(context.deviceId())
                    }
                }
            )
        }
    }

    fun updateEmailNotifications(value: Boolean) {
        val updatedProfile = profile?.copy(
             emailNotifications = value
        )
        if(updatedProfile != null) {
            profileSharedViewModel.updateProfile(
                updatedProfile,
                successCallback = {
                }
            )
        }
    }


    SnackbarMessageHandler(snackbarMessage = toastMessage, onDismissSnackbar = { viewModel.dismissToast() })

    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ToggleNotificationsTiramisu(
                onChange = {value ->
                    updatePushNotifications(value)
                }
            )
        } else {
            ToggleNotifications(
                profileNotificationValue = profile!!.pushNotifications,
                onChange = {
                    updatePushNotifications(it)
                }
            )
        }


        ToggleItem(itemText = "Email Notifications", onChange = {
            updateEmailNotifications(it)
        }, value = profile?.emailNotifications!!)
    }
}

@Composable
private fun ToggleNotifications(
    modifier: Modifier = Modifier,
    onChange: (Boolean) -> Unit,
    profileNotificationValue: Boolean
) {
    val context = LocalContext.current

    // Check if notifications are enabled
    val areNotificationsEnabled = remember {
        mutableStateOf(NotificationManagerCompat.from(context).areNotificationsEnabled())
    }
    if (!areNotificationsEnabled.value) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Notifications are disabled. Please enable them in settings.", textAlign = TextAlign.Center)
            Button(onClick = {
                // Open the notification settings for the app
                val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                    putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
                }
                context.startActivity(intent)
            }) {
                Text("Open Settings")
            }
        }
    }
    ToggleItem(itemText = "Push Notifications", onChange = {
        onChange(it)
    }, value = profileNotificationValue,
        isEnabled = areNotificationsEnabled.value
    )
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun ToggleNotificationsTiramisu(
    modifier: Modifier = Modifier,
    onChange: (Boolean) -> Unit
) {
    val notificationsPermissionState = rememberPermissionState(permission = android.Manifest.permission.POST_NOTIFICATIONS)

    ToggleItem(itemText = "Push Notifications", onChange = {value ->
        if(!notificationsPermissionState.status.isGranted) {
            notificationsPermissionState.launchPermissionRequest()
        }
        onChange(value)

    },
        value = notificationsPermissionState.status.isGranted,
    )

}

@Composable
private fun ToggleItem(
    modifier: Modifier = Modifier,
    itemText: String,
    value: Boolean,
    onChange: ((Boolean)) -> Unit,
    isEnabled: Boolean = true
) {
    val isChecked = remember {
        mutableStateOf(value)
    }
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = itemText)
        Switch(checked = isChecked.value, enabled = isEnabled , onCheckedChange = {
            isChecked.value = it
            onChange(it)
        })
    }
}

@Preview
@Composable
fun NotificationSettingsScreenPreview() {
    GaloreTheme {
        NotificationSettings()
    }
}


