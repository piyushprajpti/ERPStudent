package com.piyushprajpti.college_erp_system.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class CustomColorScheme(
    val primary: Color,
    val secondary: Color,
    val primaryText: Color,
    val secondaryText: Color,
    val primaryBack: Color,
    val secondaryBack: Color,
    val lightGray: Color,
    val mediumGray: Color,
    val darkGray: Color,
    val errorRed: Color,
    val successGreen: Color,
    val warningYellow: Color,
    val white: Color,
    val black: Color,
)

val LocalCustomColorScheme = staticCompositionLocalOf<CustomColorScheme> {
    error("No custom color provided")
}

val CustomDarkColorScheme = CustomColorScheme(
    primary = Color(0xFFE16349),
    secondary = Color(0xFFFFDD88),
    primaryText = Color(0xFF000000),
    secondaryText = Color(0xFF404040),
    primaryBack = Color(0xFFFAF8F4),
    secondaryBack = Color(0xFFEEEEEA),
    lightGray = Color.LightGray,
    mediumGray = Color(0xFFB1B1B1),
    darkGray = Color.DarkGray,
    errorRed = Color.Red,
    successGreen = Color.Green,
    warningYellow = Color.Yellow,
    white = Color(0xFFFFFFFF),
    black = Color(0xFF000000),
)

val CustomLightColorScheme = CustomColorScheme(
    primary = Color(0xFFE16349),
    secondary = Color(0xFFFFDD88),
    primaryText = Color(0xFF000000),
    secondaryText = Color(0xFF404040),
    primaryBack = Color(0xFFFAF8F4),
    secondaryBack = Color(0xFFEEEEEA),
    lightGray = Color.LightGray,
    mediumGray = Color(0xFFB1B1B1),
    darkGray = Color.DarkGray,
    errorRed = Color.Red,
    successGreen = Color.Green,
    warningYellow = Color.Yellow,
    white = Color(0xFFFFFFFF),
    black = Color(0xFF000000),
)
