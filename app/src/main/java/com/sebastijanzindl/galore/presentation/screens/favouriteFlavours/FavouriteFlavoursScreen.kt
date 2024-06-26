package com.sebastijanzindl.galore.presentation.screens.favouriteFlavours

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sebastijanzindl.galore.presentation.component.FlavourButton
import com.sebastijanzindl.galore.presentation.component.LoadingSpinner
import com.sebastijanzindl.galore.presentation.component.Logo
import com.sebastijanzindl.galore.presentation.component.SnackbarMessageHandler
import com.sebastijanzindl.galore.ui.theme.GaloreTheme


@Composable
fun FavouriteFlavoursScreen(
    modifier: Modifier = Modifier,
    navigateToAllSet: () -> Unit,
    viewModel: FavouriteFlavoursScreenViewModel = hiltViewModel()
) {
    val flavours by viewModel.allFlavours.collectAsState();
    val isLoading by viewModel.isLoading.collectAsState()
    val toastMessage by viewModel.toastMessage.collectAsState();

    val userFavouriteFlavours = remember {
        mutableStateListOf<String>();
    }

    fun favouriteFlavour (flavourName: String ) {
        if(userFavouriteFlavours.contains(flavourName)) {
            userFavouriteFlavours.remove(flavourName)
        } else {
            userFavouriteFlavours.add(flavourName)
        }
    }

    SnackbarMessageHandler(
        snackbarMessage = toastMessage,
        onDismissSnackbar = {
            viewModel.dismissToastMessage()
        }
    )

    fun continueToNextStep() {
        viewModel.submitFlavours(
            flavourIds = userFavouriteFlavours,
            onSuccessCallback = navigateToAllSet
        )
    }

    Scaffold (
        modifier = modifier.fillMaxSize()
    ) {
        if(isLoading) {
            LoadingSpinner(shouldShow = isLoading)
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding(), bottom = it.calculateBottomPadding()),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Logo()

                Text(
                    style = MaterialTheme.typography.displaySmall,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    text = "Choose your favourite flavours"
                )
                LazyVerticalGrid(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    items(flavours) {flavour ->
                        FlavourButton(
                            onClick = { favouriteFlavour(flavour.id) },
                            buttonText = flavour.name,
                            isInList = userFavouriteFlavours.contains(flavour.id)
                        )
                    }
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    enabled = userFavouriteFlavours.isNotEmpty(),
                    onClick = {
                        continueToNextStep()
                    }
                ) {
                    Text(text = "Continue")
                }
            }
        }
    }
}




@Preview(apiLevel = 33)
@Composable
private fun ButtonPreview() {
    GaloreTheme {
        FlavourButton( onClick =  {}, buttonText = "Spicy", isInList = false)
    }
}

@Preview(apiLevel = 33)
@Composable
private fun Preview1() {
    GaloreTheme {
        FavouriteFlavoursScreen(
            navigateToAllSet = {}
        )
    }
}