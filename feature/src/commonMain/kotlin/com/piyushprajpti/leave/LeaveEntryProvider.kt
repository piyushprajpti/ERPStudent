package com.piyushprajpti.leave

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.piyushprajpti.core.navigation.NavRoutes
import com.piyushprajpti.leave.leave_apply.ApplyLeaveScreen
import com.piyushprajpti.leave.leave_history.LeaveHistoryScreen

fun leaveEntryProvider(
    key: NavKey,
    backStack: NavBackStack<NavKey>
): NavEntry<NavKey>? {
    return when (key) {
        is NavRoutes.LeaveHistory -> NavEntry(key) {
            LeaveHistoryScreen(
                onBackClick = { backStack.removeLast() },
                onAddClick = { backStack.add(NavRoutes.ApplyLeave) }
            )
        }

        is NavRoutes.ApplyLeave -> NavEntry(key) {
            ApplyLeaveScreen(
                onBackClick = { backStack.removeLast() }
            )
        }

        else -> null
    }

}