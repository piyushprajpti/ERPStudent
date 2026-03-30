package com.piyushprajpti.feature.marks

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.piyushprajpti.core.navigation.NavRoutes
import com.piyushprajpti.feature.marks.grade_card.GradeCardScreen
import com.piyushprajpti.feature.marks.overview.MarksOverviewScreen

fun marksEntryProvider(
    key: NavKey,
    backStack: NavBackStack<NavKey>
): NavEntry<NavKey>? {
    return when (key) {
        is NavRoutes.MarksOverview -> NavEntry(key) {
            MarksOverviewScreen(
                onBackClick = { backStack.removeLast() },
                onGradeCardClick = {backStack.add(NavRoutes.GradeCard) }
            )
        }

        is NavRoutes.GradeCard -> NavEntry(key) {
            GradeCardScreen(
                onBackClick = { backStack.removeLast() }
            )
        }

        else -> null
    }
}