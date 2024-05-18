package com.sebastijanzindl.galore.presentation.screens.notifications

import android.util.Log
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
    val profile = profileSharedViewModel.userProfile.collectAsState();

    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ToggleItem(itemText = "Push Notifications", onChange = {
            val updatedProfile = profile.value?.copy(
                pushNotifications = it
            )
            if(updatedProfile != null) {
                profileSharedViewModel.updateProfile(
                    updatedProfile,
                    successCallback = {
                    }
                )
            }

        }, value = profile.value?.pushNotifications!!)

        ToggleItem(itemText = "Email Notifications", onChange = {
            val updatedProfile = profile.value?.copy(
                emailNotifications = it
            )
            if (updatedProfile != null) {
                Log.i("Updated Profile", updatedProfile.toString());
                profileSharedViewModel.updateProfile(
                    updatedProfile,
                    successCallback = {}
                )
            }
        }, value = profile.value?.emailNotifications!!)
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


