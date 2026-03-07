package com.piyushprajpti.erpstudent

import android.app.Application
import com.piyushprajpti.erpstudent.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ERPApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ERPApplication)
            modules(appModule)
        }
    }
}