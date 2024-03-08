package com.sebastijanzindl.galore.presentation.screens.enablePushNotifications

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sebastijanzindl.galore.R
import com.sebastijanzindl.galore.presentation.components.Logo
import com.sebastijanzindl.galore.ui.theme.GaloreTheme
import com.sebastijanzindl.galore.presentation.viewmodels.MainViewModel

@Composable
fun EnablePushNotificationScreen(
    modifier: Modifier = Modifier,
    navigateToFavouriteFlavours: () -> Unit,
    appViewModel: MainViewModel = hiltViewModel<MainViewModel>()
) {
    val lottieSpec: LottieCompositionSpec = LottieCompositionSpec.RawRes(R.raw.enable_notifications_lottie)
    val composition by rememberLottieComposition(lottieSpec)

    if(appViewModel.enabledNotifications) {
        navigateToFavouriteFlavours();
    }

    val openPremissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {
            appViewModel.setHasEnabledNotifications(it)
            navigateToFavouriteFlavours();
        }
    )

    Scaffold (
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(
                    top = it.calculateTopPadding(),
                    bottom = it.calculateBottomPadding()
                )
                .fillMaxSize()
        ) {
            Logo()
            LottieAnimation(
                modifier = Modifier.fillMaxWidth(),
                composition = composition,
                iterations = LottieConstants.IterateForever,
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Enable Push Notifications",
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = "Do you want to get notified when new cocktails are recommended to you?",
                    modifier = Modifier.width(300.dp),
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center
                )

            }
            Spacer(modifier = Modifier.size(8.dp))

            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Button(onClick = {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        openPremissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    }
                }) {
                    Text(
                        text = "Notify Me",
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
                TextButton(onClick = {
                    navigateToFavouriteFlavours()
                }) {
                    Text(
                        text = "Don't Notify Me"
                    )
                }

            }

        }
    }
}

@Composable
@Preview(apiLevel = 33)
private fun Preview1() {
    GaloreTheme {
        EnablePushNotificationScreen(
            navigateToFavouriteFlavours =  {}
        )
    }
}