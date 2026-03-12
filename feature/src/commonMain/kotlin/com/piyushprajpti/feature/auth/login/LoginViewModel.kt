package com.piyushprajpti.feature.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onusant.learnohub.utils.message_helper.MessageHelper
import com.piyushprajpti.domain.usecase.auth.GetSavedTenantUseCase
import com.piyushprajpti.feature.utils.loading_helper.LoadingHelper
import com.piyushprajpti.feature.utils.message_helper.model.Alert
import com.piyushprajpti.feature.utils.message_helper.model.AlertMessageType
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoginViewModel(
    private val getSavedTenantUseCase: GetSavedTenantUseCase
) : ViewModel() {
    
    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(LoginState())
    val state = _state.onStart {
        loadTenant()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = LoginState()
    )

    private fun loadTenant() {
        viewModelScope.launch {
            loadingHelper.setLoading(true)
            
            // Timeout logic
            val timeoutJob = launch {
                delay(5000)
                if (_state.value.tenant == null) {
                    loadingHelper.setLoading(false)
                    messageHelper.setAlert(Alert(
                        message = AlertMessageType.String("Tenant not found or taking too long to load.")
                    ))
                }
            }

            getSavedTenantUseCase().collectLatest { tenant ->
                if (tenant != null) {
                    _state.update { it.copy(tenant = tenant) }
                    loadingHelper.setLoading(false)
                    timeoutJob.cancel()
                }
            }
        }
    }

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnEmailChange -> {
                _state.update { it.copy(email = event.email) }
            }
            is LoginEvent.OnPasswordChange -> {
                _state.update { it.copy(password = event.password) }
            }
            LoginEvent.OnTogglePasswordVisibility -> {
                _state.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
            }
            LoginEvent.OnLoginClick -> {
                login()
            }
            LoginEvent.OnForgotPasswordClick -> {
                // handle forgot password
            }
            LoginEvent.OnContactAdminClick -> {
                // handle contact admin
            }
        }
    }

    private fun login() {
        setLoading(true)
        // Perform login logic using _state.value.tenant?.id, email, and password
    }

    private fun setLoading(value: Boolean) {
        _state.update { it.copy(isLoading = value) }
    }
}
