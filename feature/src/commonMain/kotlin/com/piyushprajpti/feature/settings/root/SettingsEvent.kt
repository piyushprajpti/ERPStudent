package com.piyushprajpti.feature.settings.root

sealed interface SettingsEvent {
    data class ToggleAllNotifications(val enabled: Boolean) : SettingsEvent
    data class ToggleAttendanceAlerts(val enabled: Boolean) : SettingsEvent
    data class ToggleClassAlerts(val enabled: Boolean) : SettingsEvent
    data class ToggleAnnouncements(val enabled: Boolean) : SettingsEvent
    data object OnViewProfileClick : SettingsEvent
    data object OnChangePasswordClick : SettingsEvent
    data object OnLogoutClick : SettingsEvent
    data object OnTermsOfServiceClick : SettingsEvent
    data object OnPrivacyPolicyClick : SettingsEvent
    data object OnContactSupportClick : SettingsEvent
}
