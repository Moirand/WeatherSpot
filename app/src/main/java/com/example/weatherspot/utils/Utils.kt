package com.example.weatherspot.utils

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

fun hasPermission(context: Context, permission: String): Boolean =
    ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED

fun convertDoubleToIntOrDouble(input: Double): Any {
    return if (input % 1 == 0.0 || input.toString().substringAfter(".")[0] == '0') {
        input.toInt()
    } else {
        input
    }
}