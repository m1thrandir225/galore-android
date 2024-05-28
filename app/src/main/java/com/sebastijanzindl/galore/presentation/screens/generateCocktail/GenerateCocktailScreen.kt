package com.sebastijanzindl.galore.presentation.screens.generateCocktail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sebastijanzindl.galore.presentation.component.FlavourButton
import com.sebastijanzindl.galore.ui.theme.GaloreTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GenerateCocktailScreen(
    modifier: Modifier = Modifier
) {
    val maxSteps = 3;
    val pagerState = rememberPagerState {
        maxSteps
    }

    val coroutineScope = rememberCoroutineScope()

    val onBackStepPress = {
        val currentPage = pagerState.currentPage
        if(currentPage > 0) {
            coroutineScope.launch {
                pagerState.animateScrollToPage(currentPage-1)
            }
        }
    }

    val onNextStepPress = {
        //Because the current page is the index not the actual number of the page we calculate itl ike that
        val currentPage = pagerState.currentPage
        if(currentPage == 2) {
            //generateFunction()
        } else {
            coroutineScope.launch {
                pagerState.animateScrollToPage(currentPage+1);
            }
        }
    }

    val selectedFlavoursIds = remember {
        mutableStateListOf<String>()
    };

    val selectedCocktailIds = remember {
       mutableStateListOf<String>()
    }



    fun onCocktailTap (cocktailId: String) {
        if(selectedCocktailIds.contains(cocktailId)) {
            selectedCocktailIds.remove(cocktailId)
        } else {
            if(selectedCocktailIds.count() < 3) {
                selectedCocktailIds.add(cocktailId)
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
       HorizontalPager(state = pagerState, modifier = Modifier
           .fillMaxWidth()
           .height(500.dp)) {page ->
           when(page) {
               0 -> SelectFlavourForm(
                   selectedFlavourIds = selectedFlavoursIds
               )
               1 -> SelectReferenceCocktailForm()
               2 -> Text(text = "Generate cocktail")
           }
       }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = if(pagerState.currentPage > 0)  Arrangement.SpaceBetween else Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if(pagerState.currentPage > 0) {
                Button(onClick = { onBackStepPress() }) {
                    Text(text = "Back")
                }
            }
            if(pagerState.currentPage != maxSteps -1) {
                Button(onClick = { onNextStepPress() }) {
                    Text(text = "Next")
                }
            }

        }
    }
}


@Composable
fun SelectFlavourForm(
    modifier: Modifier = Modifier,
   selectedFlavourIds: MutableList<String>
) {
    val flavours: List<String> = listOf(
        "Sweet", "Bitter", "Savoury", "Sour",
        "Spicy", "Furity", "Smokey", "Herbaceous"
    )

    fun onFlavourTap (flavourId: String) {
        if(selectedFlavourIds.contains(flavourId)) {
            selectedFlavourIds.remove(flavourId)
        } else {
            if(selectedFlavourIds.count() < 3) {
                selectedFlavourIds.add(flavourId)
            }
        }
    }
    LazyVerticalGrid(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        columns = GridCells.Fixed(2)
    ) {
        items(flavours) { item ->
            FlavourButton(
                onClick = {
                          onFlavourTap(item)
                },
                buttonText = item,
                isInList = selectedFlavourIds.contains(item)
            )
        }
    }
}

@Composable
fun SelectReferenceCocktailForm() {
    Text(text = "Select Cocktail References Form")
}


@Preview
@Composable
private fun GenerateCocktailScreenPreview() {
    GaloreTheme {
        GenerateCocktailScreen()
    }
}