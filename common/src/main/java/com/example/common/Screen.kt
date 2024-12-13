package com.example.common

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object OtpVerification : Screen("otp_verification/{fullName}-{email}-{phoneNumber}-{password}") {
        fun createRoute(fullName: String, email: String, phoneNumber: String, password: String) =
            "otp_verification/$fullName-$email-$phoneNumber-$password"
    }
    data object SuccessRegistration : Screen("success_registration")
}