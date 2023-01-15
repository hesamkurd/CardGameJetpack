package ir.mamhesam.cardgamejetpack.presentation.screens.details

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ir.mamhesam.cardgamejetpack.util.Constants.BASE_URL
import ir.mamhesam.cardgamejetpack.util.PaletteGenerator
import kotlinx.coroutines.flow.collectLatest

@ExperimentalMaterialApi
@Composable
fun DetailsScreen(
    navController : NavHostController ,
    detailsViewModel : DetailsViewModel = hiltViewModel()
)
{
    val selectedHero by detailsViewModel.selectedHero.collectAsState()
    val colorPalette by detailsViewModel.colorPalette
    
    if (colorPalette.isNotEmpty())
    {
        DetailsContent(
            navController = navController ,
            selectedHero = selectedHero,
            colors = colorPalette
        )
    } else
    {
        detailsViewModel.generateColorPalette()
    }
    
    val context = LocalContext.current
    
    LaunchedEffect(key1 = true) {
        detailsViewModel.uiEvent.collectLatest { event ->
            when (event)
            {
                is UiEvent.GenerateColorPalette ->
                {
                    val bitmap = PaletteGenerator.convertImageUrlToBitmap(
                        imageUrl = "$BASE_URL${selectedHero?.image}" ,
                        context = context
                    )
                    if (bitmap != null)
                    {
                        detailsViewModel.setColorPalette(
                            colors = PaletteGenerator.extractColorsFromBitmap(
                                bitmap = bitmap
                            )
                        )
                    }
                }
            }
        }
    }
    
}

