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
    primaryBackground = Color(0xFF000000),
    secondaryBackground = Color(0xFF121212),
    primaryText = Color(0xFFF5F5F5),
    secondaryText = Color(0xFFB0B0B0),
    tertiaryText = Color(0xFF757575),
    outline = Color(0xFF2C2C2C),
    error = Color(0xFFCF6679),
    success = Color(0xFF81C784),
    warning = Color(0xFFFFB74D),
    info = Color(0xFF64B5F6),
    white = Color(0xFFFFFFFF),
    black = Color(0xFF000000),
)

val LightThemeColorScheme = CustomColorScheme(
    primary = Color(0xFFE16349),
    primaryBackground = Color(0xFFFFFFFF),
    secondaryBackground = Color(0xFFF6F8FA),
    primaryText = Color(0xFF24292F),
    secondaryText = Color(0xFF57606A),
    tertiaryText = Color(0xFF6E7781),
    outline = Color(0xFFD0D7DE),
    error = Color(0xFFCF222E),
    success = Color(0xFF1A7F37),
    warning = Color(0xFF9A6700),
    info = Color(0xFF0969DA),
    white = Color(0xFFFFFFFF),
    black = Color(0xFF000000),
)

val CustomDarkColorScheme = DarkThemeColorScheme
val CustomLightColorScheme = LightThemeColorScheme
