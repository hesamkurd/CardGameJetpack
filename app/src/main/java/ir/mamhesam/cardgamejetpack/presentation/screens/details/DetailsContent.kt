package ir.mamhesam.cardgamejetpack.presentation.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.navigation.NavHostController
import ir.mamhesam.cardgamejetpack.domain.model.Hero
import ir.mamhesam.cardgamejetpack.R
import ir.mamhesam.cardgamejetpack.presentation.components.InfoBox
import ir.mamhesam.cardgamejetpack.presentation.components.OrderedList
import ir.mamhesam.cardgamejetpack.ui.theme.*
import ir.mamhesam.cardgamejetpack.util.Constants

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
    
    BottomSheetScaffold(
        scaffoldState = scaffoldState ,
        sheetPeekHeight = MIN_SHEET_HEIGHT ,
        sheetContent = {
            selectedHero?.let { BottomSheetContent(selectedHero = it) }
        } ,
        content = {} ,
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
