package com.application.tcgmy.views.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.application.tcgmy.R

@Composable
fun SplashScreen(
    viewModel: SplashScreenViewModel = hiltViewModel(),
    onNavigate: () -> Unit
) {
    val splashState by viewModel.splashState.collectAsStateWithLifecycle()
    val isLoading by rememberUpdatedState(newValue = splashState.isLoading)

    val resourceId = remember { mutableIntStateOf(if (isLoading) R.raw.splash_animation else R.raw.splash_error) }
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(resourceId.intValue)
    )

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    LaunchedEffect(key1 = true) {
        viewModel.doHealthCheck()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (splashState.isLoading)
        {
            LottieAnimation(
                composition = composition,
                progress = progress,
                modifier = Modifier.size(400.dp)
            )

            Text(
                modifier = Modifier.padding(10.dp),
                text = "Please wait while we prepare the app",
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Italic
            )
        }
        else {
            if (splashState.isHealthy) {
                onNavigate()
            }
            else {
                resourceId.intValue = R.raw.splash_error

                LottieAnimation(
                    composition = composition,
                    progress = progress,
                    modifier = Modifier.size(400.dp)
                )

                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "Please check your internet connection and restart the app (Code: ${splashState.errorCode})",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}