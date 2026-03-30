package com.piyushprajpti.core.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface NavRoutes: NavKey  {
    @Serializable
    data object Splash : NavRoutes, NavKey

    // auth
    @Serializable
    data object Tenant : NavRoutes, NavKey
    @Serializable
    data object Login : NavRoutes, NavKey

    // common
    @Serializable
    data object ResetPassword : NavRoutes, NavKey
    @Serializable
    data object Support : NavRoutes, NavKey

    // main
    @Serializable
    data object Home : NavRoutes, NavKey

    @Serializable
    data object Attendance : NavRoutes, NavKey

    @Serializable
    data object Schedule : NavRoutes, NavKey

    @Serializable
    data object Settings : NavRoutes, NavKey

    // settings
    @Serializable
    data object Profile: NavRoutes, NavKey
    @Serializable
    data object ChangePassword: NavRoutes, NavKey

    // notifications
    @Serializable
    data object Notifications: NavRoutes, NavKey

    // leave
    @Serializable
    data object LeaveHistory : NavRoutes, NavKey
    @Serializable
    data object ApplyLeave : NavRoutes, NavKey

    // fees
    @Serializable
    data object FeesSummary : NavRoutes, NavKey
    @Serializable
    data object FeesPayment : NavRoutes, NavKey
    @Serializable
    data object FeesHistory : NavRoutes, NavKey
    @Serializable
    data object Library : NavRoutes, NavKey
    @Serializable
    data object Documents : NavRoutes, NavKey
    @Serializable
    data object Marks : NavRoutes, NavKey
    @Serializable
    data object Exam : NavRoutes, NavKey
    @Serializable
    data object Hostel : NavRoutes, NavKey
    @Serializable
    data object Events : NavRoutes, NavKey
    @Serializable
    data object Assignment : NavRoutes, NavKey
    @Serializable
    data object Transport : NavRoutes, NavKey
}
