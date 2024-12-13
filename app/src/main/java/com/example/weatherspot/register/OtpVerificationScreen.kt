package com.example.weatherspot.register

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.common.ui.theme.WeatherSpotTheme
import com.example.common.ui.theme.Purple1
import kotlinx.coroutines.delay

@Composable
fun OtpVerificationScreen(
    modifier: Modifier = Modifier,
    fullName: String? = "",
    email: String? = "andreansyah1815@gmail.com",
    phoneNumber: String? = "",
    password: String? = "",
    navigateToSuccessRegistration: () -> Unit = {}
) {
    val otpLength = 6
    var otpCode by remember { mutableStateOf(List(otpLength) { "" }) }

    // State untuk menghitung mundur
    var remainingTime by remember { mutableIntStateOf(30) }
    var canResend by remember { mutableStateOf(false) }

    // Timer untuk menghitung mundur
    LaunchedEffect(Unit) {
        while (remainingTime > 0) {
            delay(1000)
            remainingTime--
        }
        canResend = true
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Spacer(modifier = Modifier.weight(.2f))

        Text(
            text = "Almost there",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = buildAnnotatedString {
                append("Please enter 6 digits code sent to your email")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(" $email")
                }
                append(" for verification")
            },
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(32.dp))

        OtpInput(
            otpLength = otpLength,
            otpValues = otpCode,
            onOtpChange = { newOtpCode ->
                otpCode = newOtpCode
            },
            onOtpComplete = { completeOtp ->
                // Lakukan verifikasi OTP di sini
                navigateToSuccessRegistration()
            }
        )
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Didn't receive any code? Resend again",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                color = if (canResend) MaterialTheme.colorScheme.primary else Color.Gray
            ),
            modifier = Modifier.clickable(enabled = canResend) {
                // Logika kirim ulang kode
                remainingTime = 30
                canResend = false
            }
        )

        if (!canResend) {
            Text(
                text = "Request new code in 00:${remainingTime.toString().padStart(2, '0')} seconds",
                style = MaterialTheme.typography.bodyLarge
            )
        } else {
            Text(
                text = "Resend code",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Purple1
                ),
                modifier = Modifier.clickable { }
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun OtpInput(
    otpLength: Int,
    otpValues: List<String> = List(otpLength) { "" },
    onOtpChange: (List<String>) -> Unit = {},
    onOtpComplete: (String) -> Unit = {}
) {
    val focusManager = LocalFocusManager.current
    val focusRequesters = remember { List(otpLength) { FocusRequester() } }

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(otpLength) { index ->
            OtpBox(
                value = otpValues[index],
                onValueChange = { newValue ->
                    // Buat salinan mutable list untuk diupdate
                    val updatedOtpValues = otpValues.toMutableList()
                    updatedOtpValues[index] = newValue

                    // Panggil callback dengan list yang diupdate
                    onOtpChange(updatedOtpValues)

                    // Logika perpindahan fokus
                    if (newValue.isNotEmpty()) {
                        if (index < otpLength - 1) {
                            focusRequesters[index + 1].requestFocus()
                        } else {
                            focusManager.clearFocus()
                        }
                    } else if (index > 0) {
                        focusRequesters[index - 1].requestFocus()
                    }

                    // Verifikasi kelengkapan input otp
                    val completeOtp = updatedOtpValues.joinToString("")
                    if (completeOtp.length == otpLength) {
                        onOtpComplete(completeOtp)
                    }
                },
                focusRequester = focusRequesters[index]
            )
        }
    }
}

@Composable
fun OtpBox(
    value: String = "",
    onValueChange: (String) -> Unit = {},
    focusRequester: FocusRequester = remember { FocusRequester() }
) {
    BasicTextField(
        value = value,
        onValueChange = {
            // Hanya terima satu digit
            if (it.length <= 1 && it.all { char -> char.isDigit() }) {
                onValueChange(it)
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        modifier = Modifier
            .focusRequester(focusRequester)
            .size(48.dp)
            .border(
                width = 1.dp,
                color = if (value.isEmpty()) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                shape = RoundedCornerShape(8.dp)
            )
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp),
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyLarge.copy(
            textAlign = TextAlign.Center
        ),
        decorationBox = { innerTextField ->
            Box(contentAlignment = Alignment.Center) {
                if (value.isEmpty()) {
                    Text(
                        text = "-",
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    )
                }
                innerTextField()
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun OtpInputFieldPreview() {
    WeatherSpotTheme {
        OtpBox()
    }
}

@Preview(showBackground = true)
@Composable
private fun OtpInputFieldsPreview() {
    WeatherSpotTheme {
        OtpInput(6)
    }
}

@Preview(showBackground = true)
@Composable
private fun OtpVerificationScreenPreview() {
    WeatherSpotTheme {
        OtpVerificationScreen()
    }
}