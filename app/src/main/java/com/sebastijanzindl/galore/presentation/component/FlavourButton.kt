package com.sebastijanzindl.galore.presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun FlavourButton(
    onClick: () -> Unit,
    buttonText: String,
    isInList: Boolean,
    isDisabled: Boolean = false,
) {

    val buttonColor: Color =  if(isInList) {
        MaterialTheme.colorScheme.inverseOnSurface
    } else {
        Color.Transparent
    }

    OutlinedButton(
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = buttonColor
        ),
        enabled = !isDisabled

    ) {
        if(!isInList) {
            Icon(Icons.Default.Add, contentDescription = "", tint = MaterialTheme.colorScheme.primary)
        }
        Text(
            text = buttonText,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}