package com.example.weatherspot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.common.Screen
import com.example.common.ui.theme.WeatherSpotTheme
import com.example.weatherspot.login.LoginScreen
import com.example.weatherspot.register.OtpVerificationScreen
import com.example.weatherspot.register.RegisterScreen
import com.example.weatherspot.register.SuccessRegistrationScreen
import com.example.weatherspot.splash.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            WeatherSpotTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        startDestination = Screen.Splash.route,
                        navController = navController
                    ) {
                        composable(Screen.Splash.route) {
                            SplashScreen(
                                navigateToLogin = {
                                    navController.navigate(Screen.Login.route) {
                                        popUpTo(navController.graph.findStartDestination().id) { inclusive = true }
                                    }
                                }
                            )
                        }
                        composable(Screen.Login.route) {
                            LoginScreen(
                                navigateToRegister = {
                                    navController.navigate(Screen.Register.route) {
                                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                        restoreState = true
                                    }
                                }
                            )
                        }
                        composable(Screen.Register.route) {
                            RegisterScreen(
                                navigateToLogin = {
                                    navController.popBackStack(Screen.Login.route, false)
                                },
                                navigateToOtpVerification = { fullName, email, phoneNumber, password ->
                                    navController.navigate(
                                        Screen.OtpVerification.createRoute(
                                            fullName,
                                            email,
                                            phoneNumber,
                                            password
                                        )
                                    ) {
                                        popUpTo(Screen.Register.route) { saveState = true }
                                        restoreState = true
                                    }
                                }
                            )
                        }
                        composable(
                            route = Screen.OtpVerification.route,
                            arguments = listOf(
                                navArgument("fullName") { type = NavType.StringType },
                                navArgument("email") { type = NavType.StringType },
                                navArgument("phoneNumber") { type = NavType.StringType },
                                navArgument("password") { type = NavType.StringType }
                            )
                        )
                        { backStackEntry ->
                            val fullName = backStackEntry.arguments?.getString("fullName")
                            val email = backStackEntry.arguments?.getString("email")
                            val phoneNumber = backStackEntry.arguments?.getString("phoneNumber")
                            val password = backStackEntry.arguments?.getString("password")
                            OtpVerificationScreen(
                                fullName = fullName,
                                email = email,
                                phoneNumber = phoneNumber,
                                password = password,
                                navigateToSuccessRegistration = {
                                    navController.navigate(Screen.SuccessRegistration.route)  {
                                        popUpTo(Screen.Register.route) { inclusive = true }
                                    }
                                }
                            )
                        }
                        composable(
                            route = Screen.SuccessRegistration.route
                        ) {
                            SuccessRegistrationScreen(
                                navigateToLogin = {
                                    navController.popBackStack(Screen.Login.route, true)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}