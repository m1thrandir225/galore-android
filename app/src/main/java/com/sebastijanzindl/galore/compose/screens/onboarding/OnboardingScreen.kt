package com.sebastijanzindl.galore.compose.screens.onboarding

import android.widget.Space
import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sebastijanzindl.galore.R
import com.sebastijanzindl.galore.compose.components.Logo
import com.sebastijanzindl.galore.ui.theme.GaloreTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    navigateToPushNotificationScreen: () -> Unit,
) {
    val maxCarouselSlides = 3;
    val carouselState = rememberPagerState {
        maxCarouselSlides
    }

    val coroutineScope = rememberCoroutineScope();

    val pagerState  = rememberPagerState {
        3
    }

    Scaffold { it ->
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(top = it.calculateTopPadding(), bottom = it.calculateBottomPadding())
                .fillMaxSize()
        ) {
            Logo()
            Carousel(pagerState = pagerState)
            Button(
                modifier = Modifier.width(200.dp),
                onClick =  {

                    val currentPage = pagerState.currentPage
                    if(currentPage < 2 ) {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(currentPage + 1);
                        }
                    } else {
                        coroutineScope.launch {
                            // navigate to personalization screen
                            pagerState.animateScrollToPage(0)
                        }
                    }

                }) {
                Text(text = "Continue")
            }

        }

    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Carousel(
    modifier: Modifier = Modifier,
    pagerState: PagerState
) {
    val slideImage = remember {
        mutableIntStateOf(R.drawable.open_doodles___dynamic_duo)
    }

    val slideText = remember {
        mutableStateOf("Choose your favourite flavours.")
    }
    val coroutineScope  = rememberCoroutineScope();


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        HorizontalPager(state = pagerState ) { page ->
            when(page) {
                0 -> {
                    slideImage.value = R.drawable.open_doodles___catching_up
                    slideText.value = "Choose your favourite flavours."
                }
                1 -> {
                    slideImage.value = R.drawable.open_doodles___dynamic_duo
                    slideText.value = "Find your next favourite drink."
                }

                2 -> {
                    slideImage.intValue = R.drawable.open_doodles___stuff_to_do
                    slideText.value = "Generate your own unique cocktail."
                }
            }

            OnboardingCarouselItem(image = slideImage.intValue, carouselText = slideText.value)
        }

        DotsIndicator(
            totalDots = 3,
            selectedIndex = pagerState.currentPage,
            selectedColor = MaterialTheme.colorScheme.primary,
            unSelectedColor = MaterialTheme.colorScheme.onBackground
        )
        

    }
}
@Composable
fun OnboardingCarouselItem(
    @DrawableRes image: Int,
    carouselText: String
) {
    val painter = painterResource(id = image)

    Column (
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(horizontal = 24.dp)
    ){
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            painter = painter,
            contentDescription = "")
        Text(
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            text = carouselText
        )
    }
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
           Dot(
               dotIndex = index,
               selectedIndex = selectedIndex,
               selectedColor,
               unSelectedColor
           )

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}

@Composable
fun Dot(
    dotIndex: Int,
    selectedIndex: Int,
    selectedColor: Color,
    unSelectedColor: Color,

) {
    val animatedWidth by animateDpAsState(
        if (dotIndex == selectedIndex) 16.dp else 8.dp, label = ""
    )

    val backgroundColor by animateColorAsState(
        if(dotIndex == selectedIndex) selectedColor else unSelectedColor, label = ""
    )

    Box(
        modifier = Modifier
            .height(8.dp)
            .requiredWidth(animatedWidth)
            .clip(CircleShape)
            .background(color = backgroundColor)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
    )
}

@Preview(apiLevel = 33)
@Composable
private fun OnboardingScreenPreview() {
    GaloreTheme {
        OnboardingScreen (navigateToPushNotificationScreen = {})


    }
}