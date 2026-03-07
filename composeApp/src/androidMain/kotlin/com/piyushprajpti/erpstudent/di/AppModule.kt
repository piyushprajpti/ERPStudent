package com.piyushprajpti.erpstudent.di

import com.piyushprajpti.core.module.networkModule
import com.piyushprajpti.erpstudent.platformModule

val appModule = listOf(
    platformModule(),
    networkModule
)