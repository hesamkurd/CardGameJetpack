package ir.mamhesam.cardgamejetpack.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import ir.mamhesam.cardgamejetpack.presentation.components.RatingWidget
import ir.mamhesam.cardgamejetpack.ui.theme.LARGE_PADDING

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
    ){
        RatingWidget(modifier = Modifier.padding(all = LARGE_PADDING), rating = 3.4)
    }
}