package com.piyushprajpti.feature

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import com.piyushprajpti.core.navigation.NavRoutes
import com.piyushprajpti.feature.attendance.AttendanceScreen
import com.piyushprajpti.feature.home.HomeScreen
import com.piyushprajpti.feature.profile.ProfileScreen
import com.piyushprajpti.feature.schedule.ScheduleScreen
import com.piyushprajpti.feature.settings.SettingsScreen

fun mainEntryProvider(
    key: NavKey,
    backStack: NavBackStack<NavKey>
): NavEntry<NavKey>? {
    return when (key) {
        is NavRoutes.Home -> NavEntry(key) {
            HomeScreen()
        }

        is NavRoutes.Attendance -> NavEntry(key) {
            AttendanceScreen()
        }

        is NavRoutes.Schedule -> NavEntry(key) {
            ScheduleScreen()
        }

        is NavRoutes.Settings -> NavEntry(key) {
            SettingsScreen()
        }

        is NavRoutes.Profile -> NavEntry(key) {
            ProfileScreen()
        }

        else -> null
    }
}
