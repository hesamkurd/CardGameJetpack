package ir.mamhesam.cardgamejetpack.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import ir.mamhesam.cardgamejetpack.presentation.common.ListContetn

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController : NavHostController,
    homeViewModel : HomeViewModel = hiltViewModel()
){
    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()
    
    Scaffold (
        topBar = {
            HomeTopBar(onSearchClicked = {})
        },
        content = {
            ListContetn(
                navController = navController,
                heroes = allHeroes
            )
        }
    )
}