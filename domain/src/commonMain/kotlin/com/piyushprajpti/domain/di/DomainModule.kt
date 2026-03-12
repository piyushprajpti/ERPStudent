package com.piyushprajpti.domain.di

import com.piyushprajpti.domain.usecase.auth.GetSavedTenantUseCase
import com.piyushprajpti.domain.usecase.auth.GetTenantListUseCase
import com.piyushprajpti.domain.usecase.auth.SaveTenantUseCase
import org.koin.dsl.module

val domainModule = module {
    // auth use cases
    factory { GetTenantListUseCase(get()) }
    factory { SaveTenantUseCase(get()) }
    factory { GetSavedTenantUseCase(get()) }
}
