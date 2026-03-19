package com.piyushprajpti.core.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

val serializableNavModule = SerializersModule {
    polymorphic(NavKey::class) {

        subclass(NavRoutes.Splash::class, NavRoutes.Splash.serializer())

        // auth
        subclass(NavRoutes.Tenant::class, NavRoutes.Tenant.serializer())
        subclass(NavRoutes.Login::class, NavRoutes.Login.serializer())

        // common
        subclass(NavRoutes.ResetPassword::class, NavRoutes.ResetPassword.serializer())
        subclass(NavRoutes.Support::class, NavRoutes.Support.serializer())

        // main tabs
        subclass(NavRoutes.Home::class, NavRoutes.Home.serializer())
        subclass(NavRoutes.Attendance::class, NavRoutes.Attendance.serializer())
        subclass(NavRoutes.Schedule::class, NavRoutes.Schedule.serializer())
        subclass(NavRoutes.Settings::class, NavRoutes.Settings.serializer())

        //settings
        subclass(NavRoutes.Profile::class, NavRoutes.Profile.serializer())
    }
}