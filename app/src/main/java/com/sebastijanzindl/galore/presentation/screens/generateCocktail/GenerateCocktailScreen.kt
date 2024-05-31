package com.sebastijanzindl.galore.presentation.screens.generateCocktail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sebastijanzindl.galore.R
import com.sebastijanzindl.galore.presentation.viewmodels.GenerateCocktailViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GenerateCocktailScreen(
    modifier: Modifier = Modifier,
    navigateToSelectFlavourScreen: () -> Unit,
    sharedGenerateCocktailViewModel: GenerateCocktailViewModel = hiltViewModel()
) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.generate_cocktail))
    Column(
        modifier = modifier.fillMaxSize().padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column (
            modifier = Modifier
                .clip(shape = RoundedCornerShape(12.dp))
                .border(2.dp, MaterialTheme.colorScheme.onPrimaryContainer, shape = RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Generate a unique cocktail",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = "Your flavours, your cocktail",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            LottieAnimation(
                modifier = Modifier.fillMaxWidth().height(300.dp),
                iterations = LottieConstants.IterateForever,
                composition = composition
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = navigateToSelectFlavourScreen
            ) {
                Text(
                    text = "Get Started",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

        }
    }

}
