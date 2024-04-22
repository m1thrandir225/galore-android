package com.sebastijanzindl.galore.presentation.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sebastijanzindl.galore.R
import com.sebastijanzindl.galore.domain.models.Cocktail
import com.sebastijanzindl.galore.presentation.component.CocktailCardType
import com.sebastijanzindl.galore.presentation.component.CocktailTagSection
import com.sebastijanzindl.galore.presentation.component.LoadingSpinner
import com.sebastijanzindl.galore.presentation.component.Logo
import com.sebastijanzindl.galore.presentation.component.MenuItem
import com.sebastijanzindl.galore.presentation.component.ProfileBottomSheet
import com.sebastijanzindl.galore.ui.theme.GaloreTheme
import kotlinx.datetime.LocalDate

data class Section(
    val cocktails: List<Cocktail>,
    val tagName: String,
    val isFeatured: Boolean = false,
)

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

    val userProfile by viewModel.userProfile.collectAsState()

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
            image = "https://images.unsplash.com/photo-1609951651556-5334e2706168?q=80&w=3087&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            createdAt = LocalDate.parse("2024-02-24").toString(),
            embeddingVector = listOf(0.10, 1.22, 1.55),
            ingredients = "Gin, Tonic",
            name = "Gin & Tonic",
            steps = ""
        ),
        Cocktail(
            id = "2",
            image = "https://plus.unsplash.com/premium_photo-1687354207716-b74e8c056def?q=80&w=3088&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            createdAt = LocalDate.parse("2024-02-24").toString(),
            embeddingVector = listOf(0.10, 1.22, 1.55),
            ingredients = "Rum & Coke",
            name = "Rum & Coke",
            steps = ""
        ),
        Cocktail(
            id = "3",
            image = "https://images.unsplash.com/photo-1551538827-9c037cb4f32a?q=80&w=2761&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            createdAt = LocalDate.parse("2024-02-24").toString(),
            embeddingVector = listOf(0.10, 1.22, 1.55),
            ingredients = "Mojito",
            name = "Mojito",
            steps = ""
        ),
        Cocktail(
            id = "4",
            image = "https://images.unsplash.com/photo-1587223962930-cb7f31384c19?q=80&w=3087&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            createdAt = LocalDate.parse("2024-02-24").toString(),
            embeddingVector = listOf(0.10, 1.22, 1.55),
            ingredients = "Martini",
            name = "Martini",
            steps = ""
        ),
        )


    val sections = listOf(
        Section(
            cocktails = cocktails,
            tagName = "Today's Picks",
            isFeatured = true,
        ),
        Section(
            cocktails = cocktails,
            tagName = "After a long day"
        ),
        Section(
            cocktails = cocktails,
            tagName = "Something Exciting"
        ),
        Section(
            cocktails = cocktails,
            tagName = "To cool down"
        )
    )

    userProfile?.let {
        Scaffold(
            modifier = modifier.nestedScroll(scrollBehaviour.nestedScrollConnection),
            topBar = {
                HomeTopAppBar(
                    scrollBehaviour = scrollBehaviour,
                    openBottomSheet = openBottomSheet
                )
            }
        ) { contentPadding ->
            LazyColumn  (
                modifier = Modifier.padding(top = contentPadding.calculateTopPadding())
            ) {

                items(sections) { section ->
                    val cocktailCardType = if(section.isFeatured) {
                        CocktailCardType.Horizontal
                    } else {
                        CocktailCardType.Vertical
                    }
                    CocktailTagSection(cocktails = section.cocktails, tagName = section.tagName, canNavigateToSection = section.isFeatured, cocktailCardType = cocktailCardType , navigateToSection = {})
                }
            }

            if(showBottomSheet) {
                ProfileBottomSheet(
                    userProfile = userProfile,
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
                        navigateToHelp()
                    }
                    MenuItem(buttonIcon = {  Icon(painterResource(id = R.drawable.logout_24px), "") }, title = "Logout") {
                        navigateToAuth()
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