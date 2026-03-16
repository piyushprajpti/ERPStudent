package com.piyushprajpti.college_erp_system.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val customColorScheme = if (darkTheme) CustomDarkColorScheme else CustomLightColorScheme

    CompositionLocalProvider(LocalCustomColorScheme provides customColorScheme) {
        MaterialTheme(
            colorScheme = darkColorScheme(
                primary = CustomTheme.colors.primary,
                onPrimary = CustomTheme.colors.white,
                background = CustomTheme.colors.primaryBackground,
                surface = CustomTheme.colors.primaryBackground,
                error = CustomTheme.colors.error,
                onSurface = CustomTheme.colors.primaryText,
                onBackground = CustomTheme.colors.primaryText,
            ),
            typography = getTypography(),
            content = content
        )
    }
}

object CustomTheme {
    val colors: CustomColorScheme
        @Composable
        get() = LocalCustomColorScheme.current
}
