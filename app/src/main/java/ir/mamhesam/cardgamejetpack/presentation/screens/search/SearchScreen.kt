package ir.mamhesam.cardgamejetpack.presentation.screens.search

import android.annotation.SuppressLint
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ir.mamhesam.cardgamejetpack.presentation.common.ListContent
import ir.mamhesam.cardgamejetpack.ui.theme.statusBarColor

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    navController : NavHostController ,
    searchViewModel : SearchViewModel = hiltViewModel()
)
{
    val searchQuery by searchViewModel.searchQuery
    val heroes = searchViewModel.searchedHeroes.collectAsLazyPagingItems()
    
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.statusBarColor
    )
    
    Scaffold(topBar = {
        SearchTopBar(text = searchQuery ,
                     onTextChange = {
                         searchViewModel.updateSearchQuery(query = it)
                     } ,
                     onSearchClicked = {
                         searchViewModel.searchHeroes(query = it)
                     } ,
                     onClosedClicked = {
                         navController.popBackStack()
                     })
    } ,
             content = {
                 ListContent(
                     heroes = heroes ,
                     navController = navController
                 )
             })
}