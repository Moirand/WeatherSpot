package com.example.weatherspot.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.common.ui.theme.Neutral1
import com.example.common.ui.theme.WeatherSpotTheme
import com.example.common.ui.theme.Purple1
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navigateToLogin: () -> Unit = {}
) {
    LaunchedEffect(true) {
        delay(2000)
        navigateToLogin()
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Purple1)
    ) {
        Image(
            painter = painterResource(id = com.example.common.R.drawable.splash_image),
            contentDescription = "Splash Image",
            modifier = Modifier.width(250.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "WeatherSpot",
            style = MaterialTheme.typography.headlineSmall.copy(
                color = Neutral1,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    WeatherSpotTheme {
        SplashScreen()
    }
}