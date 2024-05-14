package com.sebastijanzindl.galore.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sebastijanzindl.galore.ui.theme.GaloreTheme

@Composable
fun LoadingSpinner(shouldShow: Boolean ) {
    var visible by remember { mutableStateOf(shouldShow) }
    AnimatedVisibility(
        visible = visible,
        enter = scaleIn(),
        exit = fadeOut()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.primaryContainer,
                trackColor = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        }
    }
}


@Preview
@Composable
private fun LoadingSpinnerPreview() {
    GaloreTheme {
        LoadingSpinner(shouldShow = true)
    }
}
