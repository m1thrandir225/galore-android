package com.sebastijanzindl.galore.presentation.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sebastijanzindl.galore.R
import com.sebastijanzindl.galore.domain.models.Cocktail
import com.sebastijanzindl.galore.presentation.component.CocktailTagSection
import com.sebastijanzindl.galore.presentation.component.LoadingSpinner
import com.sebastijanzindl.galore.presentation.component.Logo
import com.sebastijanzindl.galore.presentation.component.MenuItem
import com.sebastijanzindl.galore.presentation.component.ProfileBottomSheet
import com.sebastijanzindl.galore.ui.theme.GaloreTheme
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navigateToAuth: () -> Unit,
    navigateToSettings: () -> Unit,
    navigateToHelp: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()

    val uiState by viewModel.uiState.collectAsState()

    val scope = rememberCoroutineScope()
    var showBottomSheet by remember {
        mutableStateOf(false)
    }
    val scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val openBottomSheet = {
        showBottomSheet = true
    }

    val dismissBottomSheet = {
        showBottomSheet = false
    }
    val cocktails = listOf(
        Cocktail(
            id = "1",
            image = "https://images.unsplash.com/photo-1712928247899-2932f4c7dea3?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            createdAt = LocalDate.parse("2024-02-24").toString(),
            embeddingVector = listOf(0.10, 1.22, 1.55),
            ingredients = "Gin, Tonic",
            name = "Gin & Tonic",
            steps = "\"{\\\"steps\\\":{\\\"1\\\":\\\"Getaglassandputgininit-about10%oftheglass\\\",\\\"2\\\":\\\"Puticeandsomelemonalongsidetheginintheglass\\\",\\\"3\\\":\\\"Finishthecocktailwiththetonic\\\"}}\""
        ),
        Cocktail(
            id = "2",
            image = "https://images.unsplash.com/photo-1712928247899-2932f4c7dea3?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            createdAt = LocalDate.parse("2024-02-24").toString(),
            embeddingVector = listOf(0.10, 1.22, 1.55),
            ingredients = "Coke & Rum",
            name = "Gin & Tonic",
            steps = "\"{\\\"steps\\\":{\\\"1\\\":\\\"Getaglassandputgininit-about10%oftheglass\\\",\\\"2\\\":\\\"Puticeandsomelemonalongsidetheginintheglass\\\",\\\"3\\\":\\\"Finishthecocktailwiththetonic\\\"}}\""
        ),
        Cocktail(
            id = "1",
            image = "https://images.unsplash.com/photo-1712928247899-2932f4c7dea3?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            createdAt = LocalDate.parse("2024-02-24").toString(),
            embeddingVector = listOf(0.10, 1.22, 1.55),
            ingredients = "Gin, Tonic",
            name = "Gin & Tonic",
            steps = "\"{\\\"steps\\\":{\\\"1\\\":\\\"Getaglassandputgininit-about10%oftheglass\\\",\\\"2\\\":\\\"Puticeandsomelemonalongsidetheginintheglass\\\",\\\"3\\\":\\\"Finishthecocktailwiththetonic\\\"}}\""
        ),
        Cocktail(
            id = "2",
            image = "https://images.unsplash.com/photo-1712928247899-2932f4c7dea3?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            createdAt = LocalDate.parse("2024-02-24").toString(),
            embeddingVector = listOf(0.10, 1.22, 1.55),
            ingredients = "Coke & Rum",
            name = "Gin & Tonic",
            steps = "\"{\\\"steps\\\":{\\\"1\\\":\\\"Getaglassandputgininit-about10%oftheglass\\\",\\\"2\\\":\\\"Puticeandsomelemonalongsidetheginintheglass\\\",\\\"3\\\":\\\"Finishthecocktailwiththetonic\\\"}}\""
        ),
        Cocktail(
            id = "1",
            image = "https://images.unsplash.com/photo-1712928247899-2932f4c7dea3?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            createdAt = LocalDate.parse("2024-02-24").toString(),
            embeddingVector = listOf(0.10, 1.22, 1.55),
            ingredients = "Gin, Tonic",
            name = "Gin & Tonic",
            steps = "\"{\\\"steps\\\":{\\\"1\\\":\\\"Getaglassandputgininit-about10%oftheglass\\\",\\\"2\\\":\\\"Puticeandsomelemonalongsidetheginintheglass\\\",\\\"3\\\":\\\"Finishthecocktailwiththetonic\\\"}}\""
        ),
        Cocktail(
            id = "2",
            image = "https://images.unsplash.com/photo-1712928247899-2932f4c7dea3?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            createdAt = LocalDate.parse("2024-02-24").toString(),
            embeddingVector = listOf(0.10, 1.22, 1.55),
            ingredients = "Coke & Rum",
            name = "Gin & Tonic",
            steps = "\"{\\\"steps\\\":{\\\"1\\\":\\\"Getaglassandputgininit-about10%oftheglass\\\",\\\"2\\\":\\\"Puticeandsomelemonalongsidetheginintheglass\\\",\\\"3\\\":\\\"Finishthecocktailwiththetonic\\\"}}\""
        ),
        Cocktail(
            id = "1",
            image = "https://images.unsplash.com/photo-1712928247899-2932f4c7dea3?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            createdAt = LocalDate.parse("2024-02-24").toString(),
            embeddingVector = listOf(0.10, 1.22, 1.55),
            ingredients = "Gin, Tonic",
            name = "Gin & Tonic",
            steps = "\"{\\\"steps\\\":{\\\"1\\\":\\\"Getaglassandputgininit-about10%oftheglass\\\",\\\"2\\\":\\\"Puticeandsomelemonalongsidetheginintheglass\\\",\\\"3\\\":\\\"Finishthecocktailwiththetonic\\\"}}\""
        ),
        Cocktail(
            id = "2",
            image = "https://images.unsplash.com/photo-1712928247899-2932f4c7dea3?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            createdAt = LocalDate.parse("2024-02-24").toString(),
            embeddingVector = listOf(0.10, 1.22, 1.55),
            ingredients = "Coke & Rum",
            name = "Gin & Tonic",
            steps = "\"{\\\"steps\\\":{\\\"1\\\":\\\"Getaglassandputgininit-about10%oftheglass\\\",\\\"2\\\":\\\"Puticeandsomelemonalongsidetheginintheglass\\\",\\\"3\\\":\\\"Finishthecocktailwiththetonic\\\"}}\""
        )
    )

    uiState?.let {
        Scaffold(
            modifier = modifier.nestedScroll(scrollBehaviour.nestedScrollConnection),
            topBar = {
                HomeTopAppBar(
                    scrollBehaviour = scrollBehaviour,
                    openBottomSheet = openBottomSheet
                )
            }
        ) { contentPadding ->
            Column (verticalArrangement = Arrangement.SpaceEvenly){
                Button(modifier = Modifier.padding(top = contentPadding.calculateTopPadding()), onClick = {
                    viewModel.logout(navigateToAuth)
                }) {
                    Text(text = "Logout")
                }

                Text(text = "Hello World");

                Text("Hello world 2");

                CocktailTagSection(cocktails = cocktails, tagName = "After a Long Day", navigateToSection = {})
            }


            if(showBottomSheet) {
                ProfileBottomSheet(
                    userProfile = uiState,
                    sheetState = sheetState,
                    onDismissRequest = dismissBottomSheet,
                    modifier = Modifier
                ) {
                    MenuItem(
                        buttonIcon = {  Icon(Icons.Default.Settings, "") },
                        title = "Settings") {
                        navigateToSettings()
                    }
                    MenuItem(buttonIcon = {  Icon(painterResource(id = R.drawable.question_mark_24px), "") }, title = "Help") {
                        navigateToHelp();
                    }
                    MenuItem(buttonIcon = {  Icon(painterResource(id = R.drawable.logout_24px), "") }, title = "Logout") {
                        navigateToAuth();
                    }
                }
            }
        }
    } ?: run {
        Scaffold {
            Column (
                modifier = Modifier
                    .padding(top = it.calculateTopPadding())
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                LoadingSpinner()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun HomeTopAppBar(
    modifier: Modifier = Modifier,
    scrollBehaviour: TopAppBarScrollBehavior,
    openBottomSheet: () -> Unit
) {
    TopAppBar(title = {
                      Row (
                          Modifier.fillMaxWidth(),
                          horizontalArrangement = Arrangement.SpaceBetween,
                          verticalAlignment = Alignment.CenterVertically
                      ) {
                          Logo(fontSize = 24.sp)
                         IconButton(onClick = { openBottomSheet() }) {
                             Icon(Icons.Filled.AccountCircle, contentDescription = "Account")
                         }
                      }
    }, scrollBehavior = scrollBehaviour)
}

@Preview(apiLevel = 33)
@Composable
private fun HomeScreenPreview() {
    GaloreTheme {
        HomeScreen(navigateToAuth = {}, navigateToHelp = {}, navigateToSettings = {})
    }
}