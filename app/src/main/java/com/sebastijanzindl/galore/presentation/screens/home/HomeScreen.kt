package com.sebastijanzindl.galore.presentation.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.sebastijanzindl.galore.ui.theme.GaloreTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen (
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navigateToAuth: () -> Unit,
){

    val scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehaviour.nestedScrollConnection),
        topBar = {
            HomeTopAppBar(
                scrollBehaviour = scrollBehaviour
            )
        }
    ) {contentPadding ->
        Button(modifier = Modifier.padding(top = contentPadding.calculateTopPadding()), onClick = {
            viewModel.logout(navigateToAuth)
        }) {
            Text(text = "Logout")
        }

    }

}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun HomeTopAppBar(
    modifier: Modifier = Modifier,
    scrollBehaviour: TopAppBarScrollBehavior
) {
    CenterAlignedTopAppBar(title =
    {
        Row (
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Galore", style=MaterialTheme.typography.displaySmall)
        }
    },
        scrollBehavior = scrollBehaviour,
        modifier = modifier,)
}

@Preview(apiLevel = 33)
@Composable
private fun HomeScreenPreview() {
    GaloreTheme {
        HomeScreen(viewModel = hiltViewModel<HomeScreenViewModel>(), navigateToAuth =  {})
    }
}