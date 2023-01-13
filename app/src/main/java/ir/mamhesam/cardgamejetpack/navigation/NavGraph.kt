package ir.mamhesam.cardgamejetpack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import ir.mamhesam.cardgamejetpack.presentation.screens.home.HomeScreen
import ir.mamhesam.cardgamejetpack.presentation.screens.splash.SplashScreen
import ir.mamhesam.cardgamejetpack.presentation.screens.welcome.WelcomeScreen
import ir.mamhesam.cardgamejetpack.util.Constants.DETAILS_ARGUMENT_KEY

@OptIn(ExperimentalPagerApi::class)
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
            HomeScreen()
        }
        composable(
            route = Screen.Details.route ,
            arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY) {
                type = NavType.IntType
            })
        ) {
        
        }
        composable(route = Screen.Search.route) {
        
        }
    }
}