package com.piyushprajpti.erpstudent.db

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomModule = module {
    single { RoomDBFactory(androidContext()).createDatabase() }
}