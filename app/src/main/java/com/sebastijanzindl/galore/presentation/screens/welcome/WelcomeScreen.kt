package com.sebastijanzindl.galore.presentation.screens.welcome


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sebastijanzindl.galore.R
import com.sebastijanzindl.galore.presentation.components.Logo

import com.sebastijanzindl.galore.ui.theme.GaloreTheme


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    onGettingStartedClick: () -> Unit
) {
    val welcomeBackground = painterResource(id = R.drawable.welcome_background)
    Box(modifier = modifier.fillMaxSize().statusBarsPadding().systemBarsPadding()) {
        Image(
            painter = welcomeBackground,
            contentDescription = "welcome background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .matchParentSize()
                .padding(bottom = 36.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,

        ){
            Text(text = "Welcome to",
                color = Color.White,
                fontFamily = FontFamily.SansSerif,
                style = MaterialTheme.typography.displayLarge
            )
            Logo(modifier = Modifier.offset(y = (-18).dp))

            Text(text = "Become your own mixology master",
                color = MaterialTheme.colorScheme.secondary,
                fontFamily = FontFamily.SansSerif,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.offset(y = (-18).dp)
            )

            Button(onClick = onGettingStartedClick) {
                Text(text = "Get Started",
                    modifier = Modifier.padding(horizontal = 36.dp )
                )
                
            }
        }
    }
}

@Preview
@Composable
private fun WelcomeScreenPreview() {
    GaloreTheme() {
        WelcomeScreen(onGettingStartedClick = {})
    }
}