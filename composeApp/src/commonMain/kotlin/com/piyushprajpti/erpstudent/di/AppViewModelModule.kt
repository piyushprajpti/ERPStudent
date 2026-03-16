package com.piyushprajpti.erpstudent.di

import com.piyushprajpti.erpstudent.AppViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appViewModelModule = module {
    viewModelOf(::AppViewModel)
}
