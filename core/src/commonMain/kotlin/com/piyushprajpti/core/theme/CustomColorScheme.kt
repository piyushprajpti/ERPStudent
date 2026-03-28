package com.piyushprajpti.college_erp_system.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class CustomColorScheme(
    val primary: Color,
    val primaryBackground: Color,
    val secondaryBackground: Color,
    val primaryText: Color,
    val secondaryText: Color,
    val tertiaryText: Color,
    val outline: Color,
    val error: Color,
    val success: Color,
    val warning: Color,
    val info: Color,
    val white: Color,
    val black: Color,
)

val LocalCustomColorScheme = staticCompositionLocalOf<CustomColorScheme> {
    error("No custom color provided")
}

val DarkThemeColorScheme = CustomColorScheme(
    primary = Color(0xFFE16349),
    primaryBackground = Color(0xFF0E0909),
    secondaryBackground = Color(0xFF1D1513),
    primaryText = Color(0xFFFFFFFF),
    secondaryText = Color(0xFFB0B0B0),
    tertiaryText = Color(0xFF757575),
    outline = Color(0xFF3D2C28),
    error = Color(0xFFC62828),
    success = Color(0xFF1B9B4E),
    warning = Color(0xFFFFD54F),
    info = Color(0xFF215ECC),
    white = Color(0xFFFFFFFF),
    black = Color(0xFF000000),
)

val CustomDarkColorScheme = DarkThemeColorScheme
val CustomLightColorScheme = DarkThemeColorScheme
