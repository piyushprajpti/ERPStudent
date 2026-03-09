package com.piyushprajpti.erpstudent.di

import com.piyushprajpti.core.module.networkModule
import com.piyushprajpti.erpstudent.platformModule
import com.piyushprajpti.feature.auth.authModule

val appModule = listOf(
    platformModule(),
    networkModule,
    authModule
)