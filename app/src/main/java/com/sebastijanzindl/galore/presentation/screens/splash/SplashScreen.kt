package com.sebastijanzindl.galore.presentation.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sebastijanzindl.galore.R
import com.sebastijanzindl.galore.ui.theme.GaloreTheme
import io.github.jan.supabase.gotrue.SessionStatus


@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: SplashScreenViewModel = hiltViewModel(),
    navigateToMain: () -> Unit,
    navigateToAuth: () -> Unit,
) {
    val lottieSpec: LottieCompositionSpec = LottieCompositionSpec.RawRes(R.raw.splash_lottie);
    val composition by rememberLottieComposition(lottieSpec)

    val lottieProgress by animateLottieCompositionAsState(composition = composition)
    val status by viewModel.sessionStatus.collectAsState();

    if (lottieProgress == 1.0f) {
        when(status) {
            is SessionStatus.Authenticated  -> {
                navigateToMain();
            } else -> {
            navigateToAuth();
            }
        }
    }

    Box (
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        LottieAnimation(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            composition = composition,
            iterations = LottieConstants.IterateForever,

        )
    }
}



@Preview(apiLevel = 33)
@Composable
private fun SplashScreenPreview() {
    GaloreTheme {
        SplashScreen(navigateToAuth = {}, navigateToMain =  {} )
    }
}



