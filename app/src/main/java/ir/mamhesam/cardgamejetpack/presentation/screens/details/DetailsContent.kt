package ir.mamhesam.cardgamejetpack.presentation.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ir.mamhesam.cardgamejetpack.domain.model.Hero
import ir.mamhesam.cardgamejetpack.R
import ir.mamhesam.cardgamejetpack.presentation.components.InfoBox
import ir.mamhesam.cardgamejetpack.presentation.components.OrderedList
import ir.mamhesam.cardgamejetpack.ui.theme.*
import ir.mamhesam.cardgamejetpack.util.Constants
import ir.mamhesam.cardgamejetpack.util.Constants.BASE_URL

@ExperimentalMaterialApi
@Composable
fun DetailsContent(
    navController : NavHostController ,
    selectedHero : Hero?
)
{
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Expanded)
    )
    val currentSheetFraction = scaffoldState.currentSheetFraction
    
    BottomSheetScaffold(
        scaffoldState = scaffoldState ,
        sheetPeekHeight = MIN_SHEET_HEIGHT ,
        sheetContent = {
            selectedHero?.let { BottomSheetContent(selectedHero = it) }
        } ,
        content = {
            selectedHero?.let { hero ->
                BackgroundContent(
                    heroImage = hero.image ,
                    imageFraction = currentSheetFraction,
                    ocClosedClicked = {
                        navController.popBackStack()
                    } ,
                )
            }
        } ,
    )
}

@Composable
fun BottomSheetContent(
    selectedHero : Hero ,
    infoBoxIconColor : Color = MaterialTheme.colors.primary ,
    sheetBackgroundColor : Color = MaterialTheme.colors.surface ,
    contentColor : Color = MaterialTheme.colors.titleColor
)
{
    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
            .padding(all = LARGE_PADDING)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LARGE_PADDING) ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(INFO_ICON_SIZE)
                    .weight(2f) ,
                painter = painterResource(id = R.drawable.logo) ,
                contentDescription = stringResource(id = R.string.app_logo) ,
                tint = contentColor
            )
            Text(
                modifier = Modifier.weight(8f) ,
                text = selectedHero.name ,
                color = contentColor ,
                fontSize = MaterialTheme.typography.h4.fontSize ,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MEDIUM_PADDING) ,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoBox(
                icon = painterResource(id = R.drawable.bolt) ,
                iconColor = infoBoxIconColor ,
                bigText = "${selectedHero.power}" ,
                smallText = stringResource(R.string.power) ,
                textColor = contentColor
            )
            InfoBox(
                icon = painterResource(id = R.drawable.calendar) ,
                iconColor = infoBoxIconColor ,
                bigText = selectedHero.month ,
                smallText = stringResource(R.string.month) ,
                textColor = contentColor
            )
            InfoBox(
                icon = painterResource(id = R.drawable.cake) ,
                iconColor = infoBoxIconColor ,
                bigText = selectedHero.day ,
                smallText = stringResource(R.string.birthday) ,
                textColor = contentColor
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth() ,
            text = stringResource(R.string.about) ,
            color = contentColor ,
            fontSize = MaterialTheme.typography.subtitle1.fontSize ,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .alpha(alpha = ContentAlpha.medium)
                .padding(bottom = MEDIUM_PADDING) ,
            text = selectedHero.about ,
            color = contentColor ,
            fontSize = MaterialTheme.typography.body1.fontSize ,
            maxLines = Constants.ABOUT_TEXT_LINES
        )
        
        Row(
            modifier = Modifier.fillMaxWidth() ,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OrderedList(
                title = stringResource(R.string.family) ,
                items = selectedHero.family ,
                textColor = contentColor
            )
            OrderedList(
                title = stringResource(R.string.abilities) ,
                items = selectedHero.abilities ,
                textColor = contentColor
            )
            OrderedList(
                title = stringResource(R.string.nature_types) ,
                items = selectedHero.natureTypes ,
                textColor = contentColor
            )
        }
    }
}

@Composable
fun BackgroundContent(
    heroImage : String ,
    imageFraction : Float = 1f ,
    backgroundColor : Color = MaterialTheme.colors.surface ,
    ocClosedClicked : () -> Unit ,
)
{
    val imageUrl = "$BASE_URL${heroImage}"
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = imageFraction + 0.4f)
                .align(Alignment.TopStart) ,
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(data = imageUrl)
                .build() ,
            placeholder = painterResource(R.drawable.placeholder) ,
            error = painterResource(R.drawable.placeholder) ,
            contentDescription = stringResource(R.string.hero_image) ,
            contentScale = ContentScale.Crop
        
        )
        Row(
            modifier = Modifier.fillMaxWidth() ,
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(modifier = Modifier.padding(all = MEDIUM_PADDING) ,
                       onClick = { ocClosedClicked() }) {
                Icon(
                    modifier = Modifier.size(INFO_ICON_SIZE) ,
                    imageVector = Icons.Default.Close ,
                    contentDescription = stringResource(id = R.string.close_icon) ,
                    tint = Color.White
                )
            }
        }
    }
}

@ExperimentalMaterialApi
val BottomSheetScaffoldState.currentSheetFraction : Float
    get()
    {
        val fraction = bottomSheetState.progress.fraction
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue
        
        return when
        {
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Collapsed -> 1f
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Expanded -> 0f
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Expanded -> 1f - fraction
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Collapsed -> 0f + fraction
            else -> fraction
        }
    }

@Composable
@Preview
fun BottomSheetContentPreview()
{
    BottomSheetContent(
        selectedHero = Hero(
            id = 1 ,
            name = "Naruto" ,
            image = "" ,
            about = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum." ,
            rating = 4.5 ,
            power = 0 ,
            month = "Oct" ,
            day = "1st" ,
            family = listOf(
                "Minato" ,
                "Kushina" ,
                "Boruto" ,
                "Himawari"
            ) ,
            abilities = listOf(
                "Sage Mode" ,
                "Shadow Clone" ,
                "Rasengan"
            ) ,
            natureTypes = listOf(
                "Earth" ,
                "Wind"
            )
        )
    )
}
