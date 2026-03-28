package com.piyushprajpti.feature.schedule

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val scheduleModule = module {
    viewModelOf(::ScheduleViewModel)
}