package com.sebastijanzindl.galore.compose.screens.onboarding

import android.widget.Space
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sebastijanzindl.galore.R

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
) {
    val slideImage = remember {
        mutableStateOf(R.drawable.welcome_background)
    }
    val pagerState = rememberPagerState {
        10
    }
    HorizontalPager(state = pagerState, ) { page ->
        when(page) {
            0 -> {
                slideImage.value = R.drawable.ic_launcher_background
            }
            1 -> {
                slideImage.value = R.drawable.welcome_background
            }

            2 -> {
                slideImage.value = R.drawable.ic_launcher_foreground
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(slideImage.value), contentDescription = "")
        }
    }

    Spacer(modifier = Modifier.padding(4.dp))

    DotsIndicator(
        totalDots = 3,
        selectedIndex = pagerState.currentPage,
        selectedColor = Color.DarkGray,
        unSelectedColor = Color.Yellow
    )
}


@Composable
fun DotsIndicator(
    totalDots : Int,
    selectedIndex : Int,
    selectedColor: Color,
    unSelectedColor: Color,
){

    LazyRow(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()

    ) {

        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .size(5.dp)
                        .clip(CircleShape)
                        .background(selectedColor)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(5.dp)
                        .clip(CircleShape)
                        .background(unSelectedColor)
                )
            }

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}