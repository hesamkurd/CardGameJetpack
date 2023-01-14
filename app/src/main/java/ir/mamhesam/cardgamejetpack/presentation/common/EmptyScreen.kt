package ir.mamhesam.cardgamejetpack.presentation.common

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.paging.LoadState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import ir.mamhesam.cardgamejetpack.R
import ir.mamhesam.cardgamejetpack.ui.theme.DarkGray
import ir.mamhesam.cardgamejetpack.ui.theme.LightGray
import ir.mamhesam.cardgamejetpack.ui.theme.NETWORK_ERROR_ICON_HEIGHT
import ir.mamhesam.cardgamejetpack.ui.theme.SMALL_PADDING
import java.net.ConnectException
import java.net.SocketTimeoutException


@Composable
fun EmptyScreen(error : LoadState.Error)
{
    val message by remember {
        mutableStateOf(parseErrorMessage(error = error))
    }
    val icon by remember {
        mutableStateOf(R.drawable.network_error)
    }
    
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim by animateFloatAsState(
        targetValue = if (startAnimation) ContentAlpha.disabled else 0f ,
        animationSpec = tween(
            durationMillis = 1000
        )
    )
    LaunchedEffect(key1 = true) {
        startAnimation = true
    }
    
    EmptyContent(
        alphaAnim = alphaAnim ,
        icon = icon ,
        message = message
    )
    
}

@Composable
fun EmptyContent(
    alphaAnim : Float ,
    icon : Int ,
    message : String
)
{
    Column(
        modifier = Modifier.fillMaxSize() ,
        verticalArrangement = Arrangement.Center ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .size(NETWORK_ERROR_ICON_HEIGHT)
                .alpha(alpha = alphaAnim) ,
            painter = painterResource(id = icon) ,
            contentDescription = stringResource(R.string.network_error_image) ,
            tint = if (isSystemInDarkTheme()) LightGray else DarkGray
        )
        Text(
            modifier = Modifier
                .padding(top = SMALL_PADDING)
                .alpha(alpha = alphaAnim) ,
            text = message ,
            color = if (isSystemInDarkTheme()) LightGray else DarkGray ,
            textAlign = TextAlign.Center ,
            fontSize = MaterialTheme.typography.subtitle1.fontSize ,
            fontWeight = FontWeight.Medium
        )
    }
    
}

fun parseErrorMessage(error : LoadState.Error) : String
{
    return when (error.error)
    {
        is SocketTimeoutException ->
        {
            "Server Unavailable"
        }
        is ConnectException ->
        {
            "Internet Unavailable"
        }
        else ->
        {
            "Unknown Error"
        }
    }
}