package com.piyushprajpti.erpstudent.di

import com.piyushprajpti.core.module.coreModule
import com.piyushprajpti.data.di.dataModule
import com.piyushprajpti.domain.di.domainModule
import com.piyushprajpti.feature.di.featureModule

val appModule = listOf(
    platformModule,
    coreModule,
    dataModule,
    domainModule,
    appViewModelModule,
    featureModule
)
