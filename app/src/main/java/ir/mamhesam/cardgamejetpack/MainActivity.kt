package ir.mamhesam.cardgamejetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ir.mamhesam.cardgamejetpack.ui.theme.CardGameJetpackTheme

class MainActivity : ComponentActivity()
{
    override fun onCreate(savedInstanceState : Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContent {
            CardGameJetpackTheme {
                
            }
        }
    }
}

