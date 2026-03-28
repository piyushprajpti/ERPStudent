package com.piyushprajpti.feature.attendance

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val attendanceModule = module {
    viewModelOf(::AttendanceViewModel)
}

