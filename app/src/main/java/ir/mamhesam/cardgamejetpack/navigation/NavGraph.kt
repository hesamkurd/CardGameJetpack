package ir.mamhesam.cardgamejetpack.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import ir.mamhesam.cardgamejetpack.presentation.screens.details.DetailsScreen
import ir.mamhesam.cardgamejetpack.presentation.screens.home.HomeScreen
import ir.mamhesam.cardgamejetpack.presentation.screens.splash.SplashScreen
import ir.mamhesam.cardgamejetpack.presentation.screens.welcome.WelcomeScreen
import ir.mamhesam.cardgamejetpack.presentation.screens.search.SearchScreen
import ir.mamhesam.cardgamejetpack.util.Constants.DETAILS_ARGUMENT_KEY
@ExperimentalMaterialApi
@Composable
fun SteupNavGraph(navController : NavHostController)
{
    NavHost(
        navController = navController ,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Details.route ,
            arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY) {
                type = NavType.IntType
            })
        ) {
            DetailsScreen(navController = navController)
        }
        composable(route = Screen.Search.route) {
            SearchScreen(navController = navController)
        }
    }
}