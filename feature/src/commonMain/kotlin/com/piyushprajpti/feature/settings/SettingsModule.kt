package com.piyushprajpti.feature.settings

import com.piyushprajpti.feature.settings.change_password.ChangePasswordViewModel
import com.piyushprajpti.feature.settings.profile.ProfileViewModel
import com.piyushprajpti.feature.settings.root.SettingsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val settingsModule = module {
    viewModelOf(::SettingsViewModel)
    viewModelOf(::ChangePasswordViewModel)
    viewModelOf(::ProfileViewModel)
}
