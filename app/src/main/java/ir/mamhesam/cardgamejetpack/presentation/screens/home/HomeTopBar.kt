package ir.mamhesam.cardgamejetpack.presentation.screens.home

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ir.mamhesam.cardgamejetpack.R
import ir.mamhesam.cardgamejetpack.ui.theme.topAppBarBackgroundColor
import ir.mamhesam.cardgamejetpack.ui.theme.topAppBarContentColor

@Composable
fun HomeTopBar(
    onSearchClicked : () -> Unit
)
{
    TopAppBar(
        title = {
            Text(
                text = "Explore" ,
                color = MaterialTheme.colors.topAppBarContentColor
            )
        } ,
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            IconButton(
                onClick = onSearchClicked
            ){
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search_icon)
                )
            }
        }
    )
}

@Preview
@Composable
fun HomeTopBarPreview(){
    HomeTopBar {  }
}

