package com.piyushprajpti.feature.assignment

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.piyushprajpti.core.navigation.NavRoutes
import com.piyushprajpti.feature.assignment.dashboard.AssignmentDashboardScreen
import com.piyushprajpti.feature.assignment.submission.AssignmentSubmissionScreen

fun assignmentEntryProvider(
    key: NavKey,
    backStack: NavBackStack<NavKey>
): NavEntry<NavKey>? {
    return when (key) {
        is NavRoutes.AssignmentDashboard -> NavEntry(key) {
            AssignmentDashboardScreen(
                onBackClick = { backStack.removeLast() },
                onAssignmentClick = { backStack.add(NavRoutes.AssignmentSubmit) }
            )
        }

        is NavRoutes.AssignmentSubmit -> NavEntry(key) {
            AssignmentSubmissionScreen(
                onBackClick = { backStack.removeLast() }
            )
        }

        else -> null
    }

}