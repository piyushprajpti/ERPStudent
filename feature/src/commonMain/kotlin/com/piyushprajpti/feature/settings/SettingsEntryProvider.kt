package com.piyushprajpti.feature.settings

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.piyushprajpti.core.navigation.NavRoutes
import com.piyushprajpti.feature.common.support.SupportScreen
import com.piyushprajpti.feature.settings.profile.ProfileScreen

fun settingsEntryProvider(
    key: NavKey,
    backStack: NavBackStack<NavKey>
): NavEntry<NavKey>? {
    return when (key) {

        is NavRoutes.Profile -> NavEntry(key) {
            ProfileScreen()
        }

        is NavRoutes.Support -> NavEntry(key) {
            SupportScreen(
                onBackClick = { backStack.removeLast() }
            )
        }

        else -> null
    }
}