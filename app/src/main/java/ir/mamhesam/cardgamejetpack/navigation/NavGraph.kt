package ir.mamhesam.cardgamejetpack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ir.mamhesam.cardgamejetpack.util.Constants.DETAILS_ARGUMENT_KEY

@Composable
fun SteupNavGraph(navController : NavHostController)
{
    NavHost(
        navController = navController ,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
        
        }
        composable(route = Screen.Welcome.route) {
        
        }
        composable(route = Screen.Home.route) {
        
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