package com.piyushprajpti.erpstudent.di

import com.piyushprajpti.core.module.networkModule
import com.piyushprajpti.data.di.dataModule
import com.piyushprajpti.domain.di.domainModule
import com.piyushprajpti.feature.auth.authModule
import com.piyushprajpti.feature.common.commonModule

val appModule = listOf(
    platformModule,
    networkModule,
    authModule,
    dataModule,
    domainModule,
    commonModule,
    appViewModelModule
)
