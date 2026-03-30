package com.piyushprajpti.feature.fees

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.piyushprajpti.core.navigation.NavRoutes
import com.piyushprajpti.feature.fees.summary.FeesSummaryScreen

fun feesEntryProvider(
    key: NavKey,
    backStack: NavBackStack<NavKey>
): NavEntry<NavKey>? {
    return when (key) {
        is NavRoutes.FeesSummary -> NavEntry(key) {
            FeesSummaryScreen(
                onBackClick = { backStack.removeLast() }
            )
        }

        else -> null
    }
}