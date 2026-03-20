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
}
