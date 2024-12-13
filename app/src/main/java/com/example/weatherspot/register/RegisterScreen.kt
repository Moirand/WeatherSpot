package com.example.weatherspot.register

import androidx.compose.foundation.Image
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
import com.example.common.ui.theme.WeatherSpotTheme
import com.example.common.ui.theme.Purple1

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    navigateToLogin: () -> Unit = {},
    navigateToOtpVerification: (fullName: String, email: String, phoneNumber: String, password: String) -> Unit = { _, _, _, _ -> }
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isTermsConditionsAgreed by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Top()
        Spacer(modifier = Modifier.weight(1f))
        Center(
            fullName = fullName,
            onFullNameChange = { fullName = it },
            email = email,
            onEmailChange = { email = it },
            phoneNumber = phoneNumber,
            onPhoneNumberChange = { phoneNumber = it },
            password = password,
            onPasswordChange = { password = it },
            isTermsConditionsAgreed = isTermsConditionsAgreed,
            onRememberMeChange = { isTermsConditionsAgreed = it }
        )
        Spacer(modifier = Modifier.weight(1f))
        Bottom(
            navigateToLogin = navigateToLogin,
            navigateToOtpVerification = {
                navigateToOtpVerification(
                    email,
                    fullName,
                    phoneNumber,
                    password
                )
            }
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
            text = "Get Started",
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = "by creating an account",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun Center(
    fullName: String = "",
    onFullNameChange: (String) -> Unit = {},
    email: String = "",
    onEmailChange: (String) -> Unit = {},
    phoneNumber: String = "",
    onPhoneNumberChange: (String) -> Unit = {},
    password: String = "",
    onPasswordChange: (String) -> Unit = {},
    isTermsConditionsAgreed: Boolean = false,
    onRememberMeChange: (Boolean) -> Unit = {},
    onTermsClick: () -> Unit = {},
    onConditionsClick: () -> Unit = {}
) {
    val annotatedText = buildAnnotatedString {
        append("By checking the box you agree to our ")

        pushStringAnnotation(tag = "Terms", annotation = "terms")
        withStyle(
            style = SpanStyle(
                color = Purple1
            )
        ) {
            append("Terms")
        }
        pop()

        append(" and ")

        pushStringAnnotation(tag = "Conditions", annotation = "conditions")
        withStyle(
            style = SpanStyle(
                color = Purple1
            )
        ) {
            append("Conditions")
        }
        pop()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        MyTextInput(
            value = fullName,
            onValueChange = onFullNameChange,
            placeholder = "Full Name",
            icon = com.example.common.R.drawable.ic_user
        )
        Spacer(modifier = Modifier.height(16.dp))
        MyTextInput(
            value = email,
            onValueChange = onEmailChange,
            placeholder = "Valid Email",
            icon = com.example.common.R.drawable.ic_mail,
            keyboardType = KeyboardType.Email
        )
        Spacer(modifier = Modifier.height(16.dp))
        MyTextInput(
            value = phoneNumber,
            onValueChange = onPhoneNumberChange,
            placeholder = "Phone Number",
            icon = com.example.common.R.drawable.ic_phone,
            keyboardType = KeyboardType.Phone
        )
        Spacer(modifier = Modifier.height(16.dp))
        MyTextInput(
            value = password,
            onValueChange = onPasswordChange,
            placeholder = "Strong Password",
            icon = com.example.common.R.drawable.ic_lock,
            keyboardType = KeyboardType.Password,
            isHideText = true
        )

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isTermsConditionsAgreed,
                onCheckedChange = onRememberMeChange
            )
            ClickableText(
                text = annotatedText,
                style = MaterialTheme.typography.bodySmall,
                onClick = { offset ->
                    annotatedText.getStringAnnotations(
                        tag = "Terms",
                        start = offset,
                        end = offset
                    ).firstOrNull()?.let {
                        onTermsClick()
                    }

                    annotatedText.getStringAnnotations(
                        tag = "Conditions",
                        start = offset,
                        end = offset
                    ).firstOrNull()?.let {
                        onConditionsClick()
                    }
                }
            )
        }
    }
}

@Composable
fun Bottom(
    navigateToLogin: () -> Unit = {},
    navigateToOtpVerification: () -> Unit = {}
) {
    val annotatedText = buildAnnotatedString {
        append("Already have an account? ")

        pushStringAnnotation(tag = "Login", annotation = "login")
        withStyle(
            style = SpanStyle(
                color = Purple1,
                fontWeight = FontWeight.Bold
            )
        ) {
            append("Log In")
        }
        pop()
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        MyButton(
            text = "Next >",
            action = { navigateToOtpVerification() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        ClickableText(
            text = annotatedText,
            style = MaterialTheme.typography.bodySmall,
            onClick = { offset ->
                annotatedText.getStringAnnotations(
                    tag = "Login",
                    start = offset,
                    end = offset
                ).firstOrNull()?.let {
                    navigateToLogin()
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
private fun RegisterScreenPreview() {
    WeatherSpotTheme {
        RegisterScreen()
    }
}