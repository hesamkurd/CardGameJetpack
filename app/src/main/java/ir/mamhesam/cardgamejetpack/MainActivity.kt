package ir.mamhesam.cardgamejetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.mamhesam.cardgamejetpack.navigation.SteupNavGraph
import ir.mamhesam.cardgamejetpack.ui.theme.CardGameJetpackTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity()
{
    private lateinit var navController : NavHostController
    
    override fun onCreate(savedInstanceState : Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContent {
            CardGameJetpackTheme {
                navController = rememberNavController()
                SteupNavGraph(navController = navController)
            }
        }
    }
}

