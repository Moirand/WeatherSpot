package com.example.weatherspot.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.common.component.MyButton
import com.example.common.ui.theme.WeatherSpotTheme

@Composable
fun SuccessRegistrationScreen(
    modifier: Modifier = Modifier,
    navigateToLogin: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Spacer(modifier = Modifier.weight(.5f))
        Text(
            text = "Your Account Has Been Created Successfully",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.weight(.5f))
        Image(
            painter = painterResource(id = com.example.common.R.drawable.img_success_registration),
            contentDescription = "Success Registration Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "You can now log in to your account",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.weight(1f))
        MyButton(
            text = "Back to Log In Screen",
            action = navigateToLogin
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SuccessRegistrationScreenPreview() {
    WeatherSpotTheme {
        SuccessRegistrationScreen()
    }
}