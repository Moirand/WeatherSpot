package com.example.common

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object Home : Screen("home")
    data object Profile : Screen("item")

    data object Detail : Screen("item/{barangId}-{image}-{title}") {
        fun createRoute(barangId: Long, image: Int, title: String) =
            "item/$barangId-$image-$title"
    }
}