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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PasswordAndSecurityScreen (
    modifier: Modifier = Modifier,
    //viewModel: PasswordAndSecurityScreenViewModel = hiltViewModel()
) {
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
                value = "",
                label = {
                        Text(text = "Current Password")
                },
                onValueChange = { nextValue ->  println(nextValue) }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                label = {
                        Text(text = "New Password")
                },
                onValueChange = { nextValue ->  println(nextValue) }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "Confirm new password",
                label = {
                        Text(text = "Confirm new password")
                },
                onValueChange = { nextValue ->  println(nextValue) }
            )
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /*TODO*/ },
        ) {
            Icon(Icons.Default.Delete, contentDescription = "Delete Icon")
            Text(text = "Delete Account")
        }
    }
}