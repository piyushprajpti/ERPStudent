package com.piyushprajpti.erpstudent.di

import com.piyushprajpti.core.module.coreModule
import com.piyushprajpti.data.di.dataModule
import com.piyushprajpti.domain.di.domainModule
import com.piyushprajpti.feature.auth.authModule
import com.piyushprajpti.feature.common.commonModule
import com.piyushprajpti.feature.settings.settingsModule

val appModule = listOf(
    platformModule,
    coreModule,
    authModule,
    dataModule,
    domainModule,
    commonModule,
    settingsModule,
    appViewModelModule
)
