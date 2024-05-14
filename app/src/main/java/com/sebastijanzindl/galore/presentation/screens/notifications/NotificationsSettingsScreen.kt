package com.sebastijanzindl.galore.presentation.screens.notifications

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sebastijanzindl.galore.ui.theme.GaloreTheme


@Composable
fun NotificationSettings(
    modifier: Modifier  = Modifier
) {
    Column {
        ToggleItem(itemText = "Push Notifications", isChecked = true) {
            println("ckecked");
        }
        ToggleItem(itemText = "Email Notifications", isChecked = true) {
            println("ckecked");
        }
    }
}

@Composable
private fun ToggleItem(
    modifier: Modifier = Modifier,
    itemText: String,
    isChecked: Boolean,
    onCheckedChange: ((Boolean)) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = itemText)
        Switch(checked = isChecked, onCheckedChange = onCheckedChange)
    }
}

@Preview
@Composable
fun NotificationSettingsScreenPreview() {
    GaloreTheme {
        NotificationSettings()
    }
}


