package com.sebastijanzindl.galore.compose.screens.allSet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sebastijanzindl.galore.R
import com.sebastijanzindl.galore.compose.components.Logo
import com.sebastijanzindl.galore.ui.theme.GaloreTheme


@Composable
fun AllSetScreen(
    modifier: Modifier = Modifier,
    navigateToMain: () -> Unit,
) {
    val lottieSpec: LottieCompositionSpec = LottieCompositionSpec.RawRes(R.raw.all_set);
    val composition by rememberLottieComposition(spec = lottieSpec);
    Scaffold {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(
                top = it.calculateTopPadding(),
                bottom = it.calculateBottomPadding()
            ).fillMaxSize()
        ) {
            Logo()

            LottieAnimation(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                composition = composition,
                iterations = LottieConstants.IterateForever
            )
            
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    text = "You're all set!"
                )
                Text(
                    style = MaterialTheme.typography.bodySmall,
                    text = "Get ready to become your own mixology master",
                    color = MaterialTheme.colorScheme.onBackground,
                )
                
            }
            
            Button(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                onClick = {
                    navigateToMain()
                }
            ) {
                Text(text = "Continue")
            }
        }
    }
}
@Preview(apiLevel = 33)
@Composable
private fun Preview1() {
    GaloreTheme {
        AllSetScreen( navigateToMain =  {});
    }
}