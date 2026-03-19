package com.piyushprajpti.feature.settings.root

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.core.http.Result
import com.piyushprajpti.core.utils.getAppVersion
import com.piyushprajpti.domain.usecase.auth.LogoutUseCase
import com.piyushprajpti.feature.utils.loading_helper.LoadingHelper
import com.piyushprajpti.feature.utils.message_helper.MessageHelper
import com.piyushprajpti.feature.utils.message_helper.model.Alert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(SettingsState())
    val state = _state.onStart {
        _state.update { it.copy(appVersion = getAppVersion()) }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SettingsState()
    )

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.ToggleAllNotifications -> {
                _state.update { it.copy(allNotifications = event.enabled) }
            }

            is SettingsEvent.ToggleAttendanceAlerts -> {
                _state.update { it.copy(attendanceAlerts = event.enabled) }
            }

            is SettingsEvent.ToggleClassAlerts -> {
                _state.update { it.copy(classAlerts = event.enabled) }
            }

            is SettingsEvent.ToggleAnnouncements -> {
                _state.update { it.copy(announcements = event.enabled) }
            }

            SettingsEvent.OnViewProfileClick -> {
                // Handle navigation
            }

            SettingsEvent.OnChangePasswordClick -> {
                // Handle navigation
            }

            SettingsEvent.OnLogoutClick -> {
                messageHelper.setAlert(
                    Alert(
                        label = "Logout",
                        message = "Are you sure want to logout?",
                        requireAction = true,
                        positiveAction = { logout() }
                    )
                )
            }

            SettingsEvent.OnTermsOfServiceClick -> {
                // Handle navigation
            }

            SettingsEvent.OnPrivacyPolicyClick -> {
                // Handle navigation
            }

            SettingsEvent.OnContactSupportClick -> {
                // Handle navigation
            }
        }
    }

    private fun logout() {
        viewModelScope.launch {
            loadingHelper.showLoading()
            when (val result = logoutUseCase.invoke()) {
                is Result.Success -> {
                    messageHelper.showMessage("Logged out successfully")
                }

                is Result.Error -> {
                    messageHelper.showError(result.error.message)
                }
            }
            loadingHelper.hideLoading()
        }
    }
}
