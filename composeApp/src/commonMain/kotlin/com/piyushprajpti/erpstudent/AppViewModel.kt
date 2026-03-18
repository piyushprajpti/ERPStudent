package com.piyushprajpti.erpstudent

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.piyushprajpti.core.constants.ACCESS_TOKEN
import com.piyushprajpti.data.local.datastore.PreferenceDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppViewModel(
    private val preferenceDataStore: PreferenceDataStore
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Loading)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    init {
        checkAuth()
    }

    private fun checkAuth() {
        viewModelScope.launch {
            preferenceDataStore.getValue(stringPreferencesKey(ACCESS_TOKEN)).collect { token ->
                Logger.e("TAG") { "checkauth viemodel token: $token" }
                if (token == null) {
                    _authState.value = AuthState.Unauthenticated
                } else {
                    _authState.value = AuthState.Authenticated
                }
                Logger.e("TAG") { "current authState: ${_authState.value}" }
            }
        }
    }
}

sealed interface AuthState {
    data object Loading : AuthState
    data object Authenticated : AuthState
    data object Unauthenticated : AuthState
}
