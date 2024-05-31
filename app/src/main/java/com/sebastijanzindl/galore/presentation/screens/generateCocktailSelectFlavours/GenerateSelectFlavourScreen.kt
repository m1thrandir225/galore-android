package com.sebastijanzindl.galore.presentation.screens.generateCocktailSelectFlavours

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sebastijanzindl.galore.presentation.component.FlavourButton
import com.sebastijanzindl.galore.presentation.component.LoadingSpinner
import com.sebastijanzindl.galore.presentation.component.SnackbarMessageHandler
import com.sebastijanzindl.galore.presentation.viewmodels.GenerateCocktailViewModel



@Composable
fun GenerateSelectFlavoursScreen(
    modifier: Modifier = Modifier,
    viewModel: GenerateSelectFlavourViewModel = hiltViewModel(),
    sharedGenerateCocktailViewModel: GenerateCocktailViewModel,
    navigateToSelectCocktailsScreen: () -> Unit,
    goBack: () -> Unit,
) {

    val userLikedFlavours by sharedGenerateCocktailViewModel.userFavouriteFlavours.collectAsState()
    val flavours by viewModel.allFlavours.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState();
    val toastMessage by viewModel.toastMessage.collectAsState()

    val userSelectedFlavours = remember {
        mutableStateListOf<String>()
    }

    SnackbarMessageHandler(snackbarMessage = toastMessage, onDismissSnackbar = { viewModel.dismissToast() })

    LaunchedEffect(userLikedFlavours) {
        if(userLikedFlavours.isNotEmpty()) {
            val items = userLikedFlavours.toMutableStateList();
            userSelectedFlavours.clear()
            items.map {
                userSelectedFlavours.add(it)
            }
        }
    }

    fun onFlavourPress(item: String) {
        if(userSelectedFlavours.contains(item)) {
            userSelectedFlavours.remove(item)
        } else {
            if(userSelectedFlavours.count() < 3) {
                userSelectedFlavours.add(item)
            }
        }
    }

    fun continueToCocktailSelection() {
        sharedGenerateCocktailViewModel.addLikedFlavours(userSelectedFlavours);
        navigateToSelectCocktailsScreen();
    }

    if(isLoading) {
        LoadingSpinner(shouldShow = isLoading)
    } else {
        Column (
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier
                .align(Alignment.Start)
                .fillMaxWidth()) {
                Text(text = "Step 1/3")
            }
            Column (
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(
                    text = "Select 3 flavours",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = "Choose three flavors you’d like your cocktail to taste like.",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                LazyVerticalGrid(
                    modifier = Modifier,
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(flavours) { flavour ->
                        val itemInList = userSelectedFlavours.contains(flavour.id)
                        FlavourButton(
                            onClick = { onFlavourPress(flavour.id) },
                            buttonText = flavour.name,
                            isInList = itemInList,
                            isDisabled = userSelectedFlavours.count() == 3 && !itemInList
                        )
                    }
                }
            }
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ){
                AnimatedVisibility(
                    visible = !isLoading,
                    enter = fadeIn()
                ) {
                    Button(
                        enabled = userSelectedFlavours.count() == 3,
                        onClick = { continueToCocktailSelection() }

                    ) {
                        Text(text = "Next")
                    }
                }

            }
        }
    }


}