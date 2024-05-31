package com.sebastijanzindl.galore.presentation.screens.generateCocktailLoading

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sebastijanzindl.galore.R
import com.sebastijanzindl.galore.presentation.viewmodels.GenerateCocktailViewModel


@Composable
fun GenerateLoadingScreen (
    modifier: Modifier,
    sharedGenerateCocktailViewModel: GenerateCocktailViewModel,
    navigateToGenerateCocktailDetailsScreen: (cocktailId: String) -> Unit,
) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.generate_cocktail_loading))

    val isGenerating by sharedGenerateCocktailViewModel.isGenerating.collectAsState()

    val generatedCocktailId by sharedGenerateCocktailViewModel.generatedCocktailId.collectAsState()

    LaunchedEffect(Unit, generatedCocktailId) {
        sharedGenerateCocktailViewModel.generateCocktail()

        if(generatedCocktailId != null) {
            navigateToGenerateCocktailDetailsScreen(generatedCocktailId!!)

        }
    }
    if(isGenerating) {
        Column (
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(12.dp))
                    .border(
                        2.dp,
                        MaterialTheme.colorScheme.onPrimaryContainer,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(12.dp),
                verticalArrangement =  Arrangement.spacedBy(24.dp)
            ) {
                Text(
                    text = "Hold on, cocktail mastery in progress...",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                LottieAnimation(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    composition = composition,
                    iterations = LottieConstants.IterateForever
                )
            }
        }
    }


}