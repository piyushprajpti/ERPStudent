package com.piyushprajpti.feature.exam

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.piyushprajpti.core.navigation.NavRoutes
import com.piyushprajpti.feature.exam.hall_ticket.ExamHallTicketScreen
import com.piyushprajpti.feature.exam.schedule.ExamScheduleScreen

fun examEntryProvider(
    key: NavKey,
    backStack: NavBackStack<NavKey>
): NavEntry<NavKey>? {
    return when (key) {
        is NavRoutes.ExamSchedule -> NavEntry(key) {
            ExamScheduleScreen(
                onBackClick = { backStack.removeLast() },
                onHallTicketClick = { backStack.add(NavRoutes.ExamHallTicket) }
            )
        }

        is NavRoutes.ExamHallTicket -> NavEntry(key) {
            ExamHallTicketScreen(
                onBackClick = { backStack.removeLast() }
            )
        }

        else -> null
    }
}