package com.piyushprajpti.erpstudent.di

import com.piyushprajpti.core.module.networkModule
import com.piyushprajpti.data.di.dataModule
import com.piyushprajpti.domain.di.domainModule
import com.piyushprajpti.erpstudent.db.roomModule
import com.piyushprajpti.feature.auth.authModule

val appModule = listOf(
    roomModule,
    networkModule,
    authModule,
    dataModule,
    domainModule
)