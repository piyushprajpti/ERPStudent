package com.piyushprajpti.feature.auth

import com.piyushprajpti.feature.auth.login.LoginViewModel
import com.piyushprajpti.feature.auth.tenant.TenantViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authModule = module {
    viewModelOf(::TenantViewModel)
    viewModelOf(::LoginViewModel)
}
