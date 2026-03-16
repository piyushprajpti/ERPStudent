package com.piyushprajpti.feature.common

import com.piyushprajpti.feature.common.reset_password.ResetPasswordViewModel
import com.piyushprajpti.feature.common.support.SupportViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val commonModule = module {
    viewModelOf(::ResetPasswordViewModel)
    viewModelOf(::SupportViewModel)
}