package com.sebastijanzindl.galore.presentation.screens.generateCocktailSelectCocktails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sebastijanzindl.galore.domain.models.CocktailCardInfo
import com.sebastijanzindl.galore.presentation.component.CocktailCard
import com.sebastijanzindl.galore.presentation.component.LoadingSpinner
import com.sebastijanzindl.galore.presentation.component.SnackbarMessageHandler
import com.sebastijanzindl.galore.presentation.viewmodels.GenerateCocktailViewModel

@Composable
fun GenerateSelectCocktailsScreen(
    modifier: Modifier = Modifier,
    viewModel: GenerateSelectCocktailsViewModel = hiltViewModel(),
    sharedGenerateCocktailViewModel: GenerateCocktailViewModel,
    navigateToGenerateLoadingScreen: () -> Unit,
    goBack: () -> Unit,
) {

    val userLikedCocktails by sharedGenerateCocktailViewModel.userFavouriteCocktails.collectAsState();
    val isLoading by viewModel.isLoading.collectAsState();
    val cocktails by viewModel.cocktails.collectAsState();
    val toastMessage by viewModel.toastMessage.collectAsState();
    val hasSearchResults by viewModel.hasSearchResult.collectAsState();

    SnackbarMessageHandler(snackbarMessage = toastMessage, onDismissSnackbar = { viewModel.dismissToast() })
    


    val searchString = remember {
        mutableStateOf("")
    }

    val selectedCocktails = remember {
        mutableStateListOf<String>()
    }
    fun submitResults() {
        sharedGenerateCocktailViewModel.addLikedCocktails(selectedCocktails);
        navigateToGenerateLoadingScreen();
    }

    fun onCocktailCardPress(item: String) {
        if(selectedCocktails.contains(item)) {
            selectedCocktails.remove(item)
        } else {
            if(selectedCocktails.count() < 3) {
                selectedCocktails.add(item)
            }
        }
    }

    val outlineTextFieldWidth: Float by animateFloatAsState(targetValue = if(searchString.value.isNotEmpty()) 0.85f else 1.0f, label = "Outline Text Field Width" )

    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
            .animateContentSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier
            .align(Alignment.Start)
            .fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(text = "Step 2/3")
                AnimatedVisibility(visible = hasSearchResults) {
                    Button(onClick = {
                        searchString.value = ""
                        selectedCocktails.clear()
                        viewModel.getInitialCocktails();
                    }) {
                        Text(text = "Clear Search")
                    }
                }
                
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(outlineTextFieldWidth),
                value = searchString.value,
                onValueChange = { newValue: String ->  searchString.value = newValue},
                label = {
                    Text(text = "Search")
                },
                placeholder = {
                    Text(text = "Search for a specific cocktail...")
                },
                supportingText = {
                    Text(text = "")
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        selectedCocktails.clear()
                        viewModel.getSearchCocktails(searchString.value)
                    }
                )
            )
            AnimatedVisibility(
                visible = searchString.value.isNotEmpty(),
                enter = slideInHorizontally(animationSpec = tween(durationMillis = 200)) { fullWidth ->
                    // Offsets the content by 1/3 of its width to the left, and slide towards right
                    // Overwrites the default animation with tween for this slide animation.
                    -fullWidth / 3
                } + fadeIn(
                    // Overwrites the default animation with tween
                    animationSpec = tween(durationMillis = 200)
                ),
                exit =  slideOutHorizontally(animationSpec = spring(stiffness = Spring.StiffnessHigh)) {
                    // Overwrites the ending position of the slide-out to 200 (pixels) to the right
                    200
                } + fadeOut()
            ) {
                FilledIconButton(
                    onClick = {
                        selectedCocktails.clear()
                        viewModel.getSearchCocktails(searchString = searchString.value)
                    }) {
                    Icon(Icons.Default.Search, contentDescription = "")
                }
            }

        }

        if(isLoading) {
            LoadingSpinner(shouldShow = isLoading)
        } else {
            if(cocktails.isNotEmpty()) {
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 300.dp, max = 500.dp),
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(cocktails) {cocktailValue ->
                        val isInList = selectedCocktails.contains(cocktailValue.name);
                        val isDisabled = selectedCocktails.count() == 3 && !isInList;
                        CocktailCard(
                            cocktail = CocktailCardInfo(
                                id = cocktailValue.id,
                                name = cocktailValue.name,
                                image = cocktailValue.image,
                            ),
                            onCardPress = { onCocktailCardPress(cocktailValue.name) },
                            isInList = isInList,
                            isDisabled = isDisabled
                        )
                    }
                }
            } else {
                Text(text = "No cocktail's found")
            }
        }

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            AnimatedVisibility(visible = !isLoading, enter = fadeIn()) {
                Button(
                    onClick = { goBack() }

                ) {
                    Text(text = "Go Back")
                }
            }
            AnimatedVisibility(
                visible = !isLoading,
                enter = fadeIn()
            ) {
                Button(
                    enabled = selectedCocktails.isNotEmpty(),
                    onClick = { submitResults() }

                ) {
                    Text(text = "Next")
                }
            }
        }
    }
}
