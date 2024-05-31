package com.sebastijanzindl.galore.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@Composable
fun FlavourButton(
    onClick: () -> Unit,
    buttonText: String,
    isInList: Boolean,
    isDisabled: Boolean = false,
) {
    
    val animatedColor by animateColorAsState(
        if(isDisabled) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onPrimary,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessLow,
        ),
        label = "color of text"
    )

    val animatedBackground = animateColorAsState(
        if(isDisabled)  MaterialTheme.colorScheme.inverseOnSurface else MaterialTheme.colorScheme.primary,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessMedium,
        ),
        label = "background of button"
    )

    Button(
        onClick = onClick,
        enabled = !isDisabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = animatedBackground.value,
            disabledContainerColor = animatedBackground.value
        ),
        modifier = Modifier.animateContentSize()
    ) {
        AnimatedVisibility(visible = !isInList) {
            Icon(Icons.Default.Add, contentDescription = "", tint = animatedColor)

        }
        Text(
            text = buttonText,
            color = animatedColor
        )
    }
}