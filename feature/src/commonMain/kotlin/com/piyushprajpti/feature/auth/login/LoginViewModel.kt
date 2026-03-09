package com.piyushprajpti.feature.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.onStart {
        // load initial data here
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = LoginState()
    )

    fun onEvent(event: LoginEvent) {
        when (event) {
            else -> {}
        }
    }

    private fun setLoading(value: Boolean) {
        _state.update { it.copy(isLoading = value) }

    }
}