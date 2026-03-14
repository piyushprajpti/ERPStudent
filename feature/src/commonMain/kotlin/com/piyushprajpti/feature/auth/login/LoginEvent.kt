package com.piyushprajpti.feature.auth.login

sealed interface LoginEvent {
    data class OnEmailChange(val email: String) : LoginEvent
    data class OnPasswordChange(val password: String) : LoginEvent
    data class OnLoginClick(val onSuccess: () -> Unit) : LoginEvent
    data object OnTogglePasswordVisibility : LoginEvent
    data object OnForgotPasswordClick : LoginEvent
    data object OnContactAdminClick : LoginEvent
}