package com.piyushprajpti.feature.auth

import com.piyushprajpti.feature.auth.login.LoginViewModel
import com.piyushprajpti.feature.auth.tenant.TenantViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    viewModel { TenantViewModel(get(), get()) }
    viewModel { LoginViewModel(get(), get()) }
}
