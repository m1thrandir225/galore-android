package com.sebastijanzindl.galore.presentation.screens.cocktailSection

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.sebastijanzindl.galore.presentation.viewmodels.SectionSharedViewModel

@Composable
fun CocktailSectionScreen (
    modifier: Modifier = Modifier,
    sharedSectionViewModel: SectionSharedViewModel = hiltViewModel()
) {
    val sectionName by sharedSectionViewModel.sectionName.collectAsState();
    val cocktails by sharedSectionViewModel.cocktails.collectAsState();

    Column {
        Text(text = sectionName);
    }
}