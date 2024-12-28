package com.example.weatherspot

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.common.Screen
import com.example.common.component.MyBottomBar
import com.example.weatherspot.home.HomeScreen
import com.example.weatherspot.login.LoginScreen
import com.example.weatherspot.register.OtpVerificationScreen
import com.example.weatherspot.register.RegisterScreen
import com.example.weatherspot.register.SuccessRegistrationScreen
import com.example.weatherspot.splash.SplashScreen

@Composable
fun WeatherSpotApp() {
    val navController = rememberNavController()
    val currentScreen = navController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        bottomBar = { if (currentScreen == Screen.Home.route) MyBottomBar() },
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
                            popUpTo(navController.graph.findStartDestination().id) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
            composable(Screen.Login.route) {
                LoginScreen(
                    navigateToRegister = {
                        navController.navigate(Screen.Register.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                        }
                    },
                    navigateToHome = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Login.route) {
                                inclusive = true
                            }
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
                        navController.navigate(Screen.SuccessRegistration.route) {
                            popUpTo(Screen.Register.route) { inclusive = true }
                        }
                    }
                )
            }
            composable(Screen.SuccessRegistration.route) {
                SuccessRegistrationScreen(
                    navigateToLogin = {
                        navController.popBackStack(Screen.Login.route, true)
                    }
                )
            }
            composable(Screen.Home.route) {
                HomeScreen()
            }
        }
    }
}