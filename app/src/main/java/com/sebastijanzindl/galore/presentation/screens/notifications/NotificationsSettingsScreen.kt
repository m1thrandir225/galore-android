package com.sebastijanzindl.galore.presentation.screens.notifications

import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sebastijanzindl.galore.presentation.viewmodels.ProfileSharedViewModel
import com.sebastijanzindl.galore.ui.theme.GaloreTheme


@Composable
fun NotificationSettings(
    modifier: Modifier  = Modifier,
    profileSharedViewModel: ProfileSharedViewModel = hiltViewModel()
) {
    val profile by profileSharedViewModel.userProfile.collectAsState();

    fun updatePushNotifications (value: Boolean) {
        val updatedProfile = profile?.copy(
            pushNotifications = value
        )
        if(updatedProfile != null) {
            profileSharedViewModel.updateProfile(
                updatedProfile,
                successCallback = {
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

    val openPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {
            updatePushNotifications(it)
        }
    )

    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ToggleItem(itemText = "Push Notifications", onChange = {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                openPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
            } else {
                updatePushNotifications(it)
            } }, value = profile?.pushNotifications!!)

        ToggleItem(itemText = "Email Notifications", onChange = {
            updateEmailNotifications(it)
        }, value = profile?.emailNotifications!!)
    }
}
@Composable
private fun ToggleItem(
    modifier: Modifier = Modifier,
    itemText: String,
    value: Boolean,
    onChange: ((Boolean)) -> Unit
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
        Switch(checked = isChecked.value, onCheckedChange = {
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


