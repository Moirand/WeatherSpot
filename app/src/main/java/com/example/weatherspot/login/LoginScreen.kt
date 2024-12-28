package com.example.weatherspot.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.common.component.MyButton
import com.example.common.component.MyTextInput
import com.example.common.ui.theme.Purple1
import com.example.common.ui.theme.WeatherSpotTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navigateToRegister: () -> Unit = {},
    navigateToHome: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isRememberMe by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Top()
        Spacer(modifier = Modifier.weight(1f))
        Center(
            email = email,
            onEmailChange = { email = it },
            password = password,
            onPasswordChange = { password = it },
            isRememberMe = isRememberMe,
            onRememberMeChange = { isRememberMe = it }
        )
        Spacer(modifier = Modifier.weight(1f))
        Bottom(
            navigateToRegister = navigateToRegister,
            navigateToHome = navigateToHome
        )
    }
}

@Composable
fun Top() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = com.example.common.R.drawable.img_login),
            contentDescription = "Login Image"
        )
        Text(
            text = "Welcome Back",
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = "sign in to access your account",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun Center(
    email: String = "",
    onEmailChange: (String) -> Unit = {},
    password: String = "",
    onPasswordChange: (String) -> Unit = {},
    isRememberMe: Boolean = false,
    onRememberMeChange: (Boolean) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        MyTextInput(
            value = email,
            onValueChange = onEmailChange,
            placeholder = "Enter Your Email",
            icon = com.example.common.R.drawable.ic_mail,
            keyboardType = KeyboardType.Email
        )
        Spacer(modifier = Modifier.height(16.dp))
        MyTextInput(
            value = password,
            onValueChange = onPasswordChange,
            placeholder = "Enter Your Password",
            icon = com.example.common.R.drawable.ic_lock,
            keyboardType = KeyboardType.Password,
            isHideText = true
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isRememberMe,
                    onCheckedChange = onRememberMeChange
                )
                Text(
                    text = "Remember me",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Text(
                text = "Forgot Password?",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Purple1,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.clickable { }
            )
        }
    }
}

@Composable
fun Bottom(
    navigateToRegister: () -> Unit = {},
    navigateToHome: () -> Unit = {}
) {
    val annotatedText = buildAnnotatedString {
        append("Don't have an account? ")

        pushStringAnnotation(tag = "Register", annotation = "register")
        withStyle(
            style = SpanStyle(
                color = Purple1,
                fontWeight = FontWeight.Bold
            )
        ) {
            append("Register now")
        }
        pop()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        MyButton(
            text = "Log In",
            action = navigateToHome
        )

        Spacer(modifier = Modifier.height(16.dp))

        ClickableText(
            text = annotatedText,
            style = MaterialTheme.typography.bodySmall,
            onClick = { offset ->
                annotatedText.getStringAnnotations(
                    tag = "Register",
                    start = offset,
                    end = offset
                ).firstOrNull()?.let {
                    navigateToRegister()
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TopPreview() {
    WeatherSpotTheme {
        Top()
    }
}

@Preview(showBackground = true)
@Composable
private fun CenterPreview() {
    WeatherSpotTheme {
        Center()
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomPreview() {
    WeatherSpotTheme {
        Bottom()
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    WeatherSpotTheme {
        LoginScreen()
    }
}