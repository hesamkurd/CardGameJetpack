package ir.mamhesam.cardgamejetpack.presentation.screens.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.google.accompanist.pager.*
import ir.mamhesam.cardgamejetpack.R
import ir.mamhesam.cardgamejetpack.domain.model.OnBoardingPages
import ir.mamhesam.cardgamejetpack.ui.theme.*
import ir.mamhesam.cardgamejetpack.util.Constants.ON_BOARDING_PAGE_COUNT

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WelcomeScreen(navController : NavHostController)
{
    val pages = listOf(
        OnBoardingPages.First ,
        OnBoardingPages.Second ,
        OnBoardingPages.Third ,
    )
    
    val pagerState = rememberPagerState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.welcomeScreenBackgroundColor)
    ) {
        HorizontalPager(
            modifier = Modifier.weight(10f) ,
            state = pagerState ,
            count = ON_BOARDING_PAGE_COUNT ,
            verticalAlignment = Alignment.Top
        ) { position ->
            PagerScreen(onBoardingPages = pages[position])
        }
        HorizontalPagerIndicator(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally) ,
            pagerState = pagerState ,
            activeColor = MaterialTheme.colors.activeIndicatorColor ,
            inactiveColor = MaterialTheme.colors.inactiveIndicatorColor ,
            indicatorWidth = PAGING_INDICATOR_WIDTH ,
            spacing = PAGING_INDICATOR_SPACING
        )
        FinishButton(
            modifier = Modifier.weight(1f),
            pagerState = pagerState
        ){
        
        }
    }
}

@Composable
fun PagerScreen(onBoardingPages : OnBoardingPages)
{
    Column(
        modifier = Modifier.fillMaxWidth() ,
        horizontalAlignment = Alignment.CenterHorizontally ,
        verticalArrangement = Arrangement.Top ,
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f) ,
            painter = painterResource(id = onBoardingPages.image) ,
            contentDescription = stringResource(R.string.on_boarding_image)
        )
        Text(
            modifier = Modifier.fillMaxWidth() ,
            text = onBoardingPages.title ,
            color = MaterialTheme.colors.titleColor ,
            fontSize = MaterialTheme.typography.h4.fontSize ,
            fontWeight = FontWeight.Bold ,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = EXTRA_LARGE_PADDING)
                .padding(top = SMALL_PADDING) ,
            text = onBoardingPages.description ,
            color = MaterialTheme.colors.descriptionColor ,
            fontSize = MaterialTheme.typography.subtitle1.fontSize ,
            fontWeight = FontWeight.Medium ,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FinishButton(
    modifier : Modifier,
    pagerState : PagerState ,
    onClick : () -> Unit
)
{
    Row(
        modifier = modifier.padding(
            horizontal = EXTRA_LARGE_PADDING
        ) ,
        verticalAlignment = Alignment.Top ,
        horizontalArrangement = Arrangement.Center ,
    ) {
        AnimatedVisibility(
            modifier = modifier.fillMaxWidth(),
            visible = pagerState.currentPage == 2
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.buttonBackgroundColor,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Finish"
                )
            }
        }
    }
}