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
        subclass(NavRoutes.ChangePassword::class, NavRoutes.ChangePassword.serializer())

        // notifications
        subclass(NavRoutes.Notifications::class, NavRoutes.Notifications.serializer())

        // quick actions
        subclass(NavRoutes.Leave::class, NavRoutes.Leave.serializer())
        subclass(NavRoutes.Fees::class, NavRoutes.Fees.serializer())
        subclass(NavRoutes.Library::class, NavRoutes.Library.serializer())
        subclass(NavRoutes.Documents::class, NavRoutes.Documents.serializer())
        subclass(NavRoutes.Marks::class, NavRoutes.Marks.serializer())
        subclass(NavRoutes.Exam::class, NavRoutes.Exam.serializer())
        subclass(NavRoutes.Hostel::class, NavRoutes.Hostel.serializer())
        subclass(NavRoutes.Events::class, NavRoutes.Events.serializer())
        subclass(NavRoutes.Assignment::class, NavRoutes.Assignment.serializer())
        subclass(NavRoutes.Transport::class, NavRoutes.Transport.serializer())
    }
}
