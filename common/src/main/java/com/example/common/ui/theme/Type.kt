package com.example.common.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.common.R

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.calibri)),
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = (-0.75).sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.calibri)),
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.calibri)),
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.calibri)),
        fontWeight = FontWeight.Light,
        fontSize = 10.sp,
        lineHeight = 23.sp,
        letterSpacing = 0.sp
    )
)