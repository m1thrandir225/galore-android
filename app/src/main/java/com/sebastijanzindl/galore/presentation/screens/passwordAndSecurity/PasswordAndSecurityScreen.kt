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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sebastijanzindl.galore.presentation.component.LoadingSpinner
import com.sebastijanzindl.galore.presentation.component.SnackbarMessageHandler
import com.sebastijanzindl.galore.presentation.viewmodels.ProfileSharedViewModel

@Composable
fun PasswordAndSecurityScreen (
    modifier: Modifier = Modifier,
    profileSharedViewModel: ProfileSharedViewModel = hiltViewModel(),
    viewModel: PasswordAndSecurityScreenViewModel = hiltViewModel(),
    navigateToAuth: () -> Unit,
) {

    val toastMessage by viewModel.toastMessage.collectAsState();
    val profile by profileSharedViewModel.userProfile.collectAsState();
    val isLoading by profileSharedViewModel.isLoading.collectAsState();

    var newPassword by remember {
        mutableStateOf("")
    }

    var confirmNewPassword by remember {
        mutableStateOf("")
    }

    SnackbarMessageHandler(
        snackbarMessage = toastMessage,
        onDismissSnackbar = { viewModel.dismissToastMessage() }
    )

    if(isLoading) {
        LoadingSpinner(shouldShow = isLoading)
    } else {
        Column(
            modifier = modifier
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = newPassword,
                    label = {
                        Text(text = "New Password")
                    },
                    onValueChange = { newValue -> newPassword = newValue }
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = confirmNewPassword,
                    label = {
                        Text(text = "Confirm new password")
                    },
                    onValueChange = { newValue -> confirmNewPassword = newValue }
                )
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    enabled = confirmNewPassword.isNotEmpty()
                            && newPassword.isNotEmpty()
                            && newPassword == confirmNewPassword,
                    onClick = {
                        viewModel.resetPassword(
                            newPassword = confirmNewPassword,
                            successCallback = {
                                profileSharedViewModel.logout(
                                    successCallback = navigateToAuth
                                )
                            })
                    }
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
}