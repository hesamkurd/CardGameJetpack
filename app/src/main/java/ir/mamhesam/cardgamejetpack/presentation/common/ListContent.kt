package ir.mamhesam.cardgamejetpack.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ir.mamhesam.cardgamejetpack.R
import ir.mamhesam.cardgamejetpack.domain.model.Hero
import ir.mamhesam.cardgamejetpack.navigation.Screen
import ir.mamhesam.cardgamejetpack.presentation.components.RatingWidget
import ir.mamhesam.cardgamejetpack.ui.theme.*
import ir.mamhesam.cardgamejetpack.util.Constants.BASE_URL

@Composable
fun ListContetn(
    navController : NavHostController ,
    heroes : LazyPagingItems<Hero>
)
{
    LazyColumn(
        contentPadding = PaddingValues(all = SMALL_PADDING) ,
        verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
    ) {
        items(items = heroes ,
              key = { hero ->
                  hero.id
              }) { hero ->
            hero?.let {
                HeroItem(
                    hero = it ,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun HeroItem(
    hero : Hero ,
    navController : NavHostController
)
{
    
    Box(modifier = Modifier
        .height(HERO_ITEM_HEIGHT)
        .clickable {
            navController.navigate(Screen.Details.passHeroId(hero.id))
        } ,
        contentAlignment = Alignment.BottomStart) {
        Surface(
            shape = RoundedCornerShape(size = LARGE_PADDING)
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize() ,
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(data = "$BASE_URL${hero.image}")
                    .build() ,
                placeholder = painterResource(R.drawable.placeholder) ,
                error = painterResource(R.drawable.placeholder) ,
                contentDescription = stringResource(R.string.hero_image) ,
                contentScale = ContentScale.Crop
            
            )
        }
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth() ,
            color = Color.Black.copy(alpha = ContentAlpha.medium) ,
            shape = RoundedCornerShape(
                bottomStart = LARGE_PADDING ,
                bottomEnd = LARGE_PADDING ,
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = MEDIUM_PADDING)
            ) {
                Text(
                    text = hero.name ,
                    color = MaterialTheme.colors.topAppBarContentColor ,
                    fontSize = MaterialTheme.typography.h5.fontSize ,
                    fontWeight = FontWeight.Bold ,
                    overflow = TextOverflow.Ellipsis ,
                    maxLines = 1 ,
                )
                Text(
                    text = hero.about ,
                    color = Color.White.copy(alpha = ContentAlpha.medium) ,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize ,
                    overflow = TextOverflow.Ellipsis ,
                    maxLines = 3 ,
                )
                Row(
                    modifier = Modifier.padding(top = SMALL_PADDING) ,
                    verticalAlignment = Alignment.CenterVertically ,
                ) {
                    RatingWidget(
                        modifier = Modifier.padding(end = SMALL_PADDING) ,
                        rating = hero.rating
                    )
                    Text(
                        text = "(${hero.rating})" ,
                        textAlign = TextAlign.Center ,
                        color = Color.White.copy(alpha = ContentAlpha.medium)
                    )
                }
            }
        }
    }
}