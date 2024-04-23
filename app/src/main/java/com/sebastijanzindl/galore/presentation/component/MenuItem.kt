package com.sebastijanzindl.galore.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sebastijanzindl.galore.ui.theme.GaloreTheme


class ButtonComposableWrapper(val composable: @Composable () -> Unit)
@Composable
fun MenuItem(
    buttonIcon: ButtonComposableWrapper?,
    title: String,
    action: () -> Unit
) {
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                action()
            }
            .padding(8.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if(buttonIcon != null) {
                OutlinedIconButton(onClick = action,
                ) {
                    buttonIcon.composable()
                }
            }
            Text(text = title, style =  MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurface)
        }
        Icon(Icons.AutoMirrored.Outlined.KeyboardArrowRight, contentDescription =  "")
    }
}

@Preview(apiLevel = 33)
@Composable
private fun MenuItemPreview() {
    GaloreTheme {
        MenuItem(
            action = {},
            title = "Settings",
            buttonIcon = ButtonComposableWrapper {
                Icon(
                    Icons.Rounded.Settings,
                    contentDescription = "Settings"
                )
            }
        )
    }
}