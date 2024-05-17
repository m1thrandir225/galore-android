package com.sebastijanzindl.galore.presentation.screens.passwordAndSecurity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun PasswordAndSecurityScreen (
    modifier: Modifier = Modifier,
    viewModel: PasswordAndSecurityScreenViewModel = hiltViewModel(),
    navigateToAuth: () -> Unit,
) {
    LaunchedEffect(viewModel.newPassword, viewModel.currentPassword, viewModel.confirmNewPassword) {
        if(viewModel.currentPassword != viewModel.newPassword) {
            if(viewModel.newPassword == viewModel.confirmNewPassword && viewModel.newPassword.isNotEmpty() && viewModel.confirmNewPassword.isNotEmpty()) {
                viewModel.updatePasswordButtonState(true)
            } else {
                viewModel.updatePasswordButtonState(false)
            }
        } else {
            viewModel.updatePasswordButtonState(false)
        }
    }
    Column (
        modifier = modifier
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column (
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.currentPassword,
                label = {
                        Text(text = "Current Password")
                },
                onValueChange = { nextValue ->  viewModel.updateCurrentPassword(nextValue) }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.newPassword,
                label = {
                        Text(text = "New Password")
                },
                onValueChange = { nextValue ->  viewModel.updateNewPassword(nextValue) }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value =  viewModel.confirmNewPassword,
                label = {
                        Text(text = "Confirm new password")
                },
                onValueChange = { nextValue ->  viewModel.updateConfirmNewPassword(nextValue) }
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                enabled = viewModel.updatePasswordButtonEnabled,
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Change Password")
            }
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.deleteAccount(
                    successCallback = navigateToAuth
                )
            },
        ) {
            Icon(Icons.Default.Delete, contentDescription = "Delete Icon")
            Text(text = "Delete Account")
        }
    }
}