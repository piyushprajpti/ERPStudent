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

    @Serializable
    data object ResetPassword : NavRoutes, NavKey

    @Serializable
    data object Support : NavRoutes, NavKey

    @Serializable
    data object Home : NavRoutes, NavKey

    // profile
    @Serializable
    data object Profile: NavRoutes, NavKey
}
