package ir.mamhesam.cardgamejetpack.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    homeViewModel : HomeViewModel = hiltViewModel()
){
    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()
    
    Scaffold (
        topBar = {
            HomeTopBar(onSearchClicked = {})
        }
    ){}
}