package ir.mamhesam.cardgamejetpack.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ir.mamhesam.cardgamejetpack.navigation.Screen
import ir.mamhesam.cardgamejetpack.presentation.common.ListContent
import ir.mamhesam.cardgamejetpack.ui.theme.statusBarColor

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController : NavHostController,
    homeViewModel : HomeViewModel = hiltViewModel()
){
    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()
    
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.statusBarColor
    )
    
    Scaffold (
        topBar = {
            HomeTopBar(onSearchClicked = {
                navController.navigate(Screen.Search.route)
            })
        },
        content = {
            ListContent(
                navController = navController,
                heroes = allHeroes
            )
        }
    )
}