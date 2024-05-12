package com.sebastijanzindl.galore.presentation.screens.accountSettings

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.sebastijanzindl.galore.R
import com.sebastijanzindl.galore.presentation.component.LoadingSpinner
import com.sebastijanzindl.galore.ui.theme.GaloreTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AccountSettingScreen(
    modifier: Modifier = Modifier,
    viewModel: AccountSettingsViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val datePickerState = rememberDatePickerState()
    val dateDialogOpen = remember {
        mutableStateOf(false)
    }

    val confirmEnabled = remember {
        derivedStateOf { datePickerState.selectedDateMillis != null }
    }
    val userProfile by viewModel.userProfile.collectAsState();
    val openDateDialog = {
        dateDialogOpen.value = true
    }

    val scrollState = rememberScrollState();

    val closeDateDialog = {
        dateDialogOpen.value = false
    }

    val updateBirthday = {
        if(datePickerState.selectedDateMillis != null) {
           viewModel.updateBirthday(datePickerState.selectedDateMillis!!)
        }
        closeDateDialog()
    }


    /** Show toast **/
    LaunchedEffect(Unit) {
        viewModel.toastMessage.collect {message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }


    if(viewModel.isLoading) {
        LoadingSpinner(shouldShow = viewModel.isLoading)
    } else {
        Column (
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(scrollState)
                .imePadding()
                .imeNestedScroll(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Column (
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) {/*TODO*/ },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(128.dp)
                        .clip(CircleShape),
                    model = userProfile!!.avatarUrl,
                    contentDescription = "Your Profile Picture",
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.profile_picture_placeholder)
                )
                Text(
                    text = "Change Profile Picture",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.email,
                label = {
                    Text(text = "Email")
                },
                onValueChange = { newValue -> viewModel.updateEmail(newValue)}
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.fullName,
                label = {
                    Text(text = "Full Name")
                },
                onValueChange = { newValue ->  viewModel.updateFullName(newValue)}
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        openDateDialog()
                    },
                enabled = false,
                value = viewModel.birthday ?: "",
                colors = OutlinedTextFieldDefaults.colors(
                    disabledTextColor = MaterialTheme.colorScheme.onSurface,
                    disabledBorderColor = MaterialTheme.colorScheme.outline,
                    disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                label = {
                    Text(text = "Birthday")
                },
                supportingText = {
                    Text(text = "DD/MM/YYYY")
                },
                onValueChange = {}
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                enabled = true,
                onClick = { viewModel.updateProfile() }
            ) {
                Text(text = "Update profile")
            }
            if(dateDialogOpen.value) {
                DatePickerDialog(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    onDismissRequest = closeDateDialog,
                    confirmButton = {
                        Button(
                            onClick = updateBirthday,
                            enabled = confirmEnabled.value
                        ) {
                            Text(text = "Confirm")
                        }
                    }
                ) {
                    DatePicker(
                        state = datePickerState
                    )
                }
            }

        }
    }

}

@Preview
@Composable
private fun AccountSettingScreenPreview() {
    GaloreTheme {
        AccountSettingScreen()
    }
}