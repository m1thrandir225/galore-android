package com.sebastijanzindl.galore.presentation.screens.accountSettings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sebastijanzindl.galore.R
import com.sebastijanzindl.galore.ui.theme.GaloreTheme
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountSettingScreen(
    modifier: Modifier = Modifier,
) {
    val datePickerState = rememberDatePickerState()
    val dateDialogOpen = remember {
        mutableStateOf(false)
    }

    val confirmEnabled = remember {
        derivedStateOf { datePickerState.selectedDateMillis != null }
    }

    val openDateDialog = {
        dateDialogOpen.value = true
    }

    val closeDateDialog = {
        dateDialogOpen.value = false
    }
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
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
                model = "",
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
            value = "",
            label = {
                    Text(text = "Email")
            },
            onValueChange = { newValue -> println(newValue) }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "",
            label = {
                Text(text = "Email")
            },
            onValueChange = { newValue -> println(newValue) }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    openDateDialog()
                },
            enabled = false,
            value = datePickerState.selectedDateMillis?.let {
                Instant.fromEpochMilliseconds(it).toLocalDateTime(
                    TimeZone.of(ZoneId.systemDefault().toString()))
            }.toString(),
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
            enabled = false,
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Update profile")
        }
        if(dateDialogOpen.value) {
            DatePickerDialog(
                modifier = Modifier.fillMaxWidth(),
                onDismissRequest = closeDateDialog,
                confirmButton = {
                    Button(
                        onClick = closeDateDialog,
                        enabled = confirmEnabled.value
                    ) {
                        Text(text = "Confirm")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
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