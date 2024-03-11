package com.sebastijanzindl.galore.presentation.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.sebastijanzindl.galore.ui.theme.benzinFont

@Preview
@Composable
fun Logo(
    modifier: Modifier = Modifier
) {
    Text(text = "galore",
        fontSize = 48.sp,
        fontFamily = benzinFont,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
    )
}