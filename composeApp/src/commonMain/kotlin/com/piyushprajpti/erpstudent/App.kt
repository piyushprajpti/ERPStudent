package com.piyushprajpti.erpstudent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.piyushprajpti.college_erp_system.theme.AppTheme
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    val viewModel: AppViewModel = koinViewModel()
    val authState by viewModel.authState.collectAsState()

    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(CustomTheme.colors.primaryBack),
            contentAlignment = Alignment.Center
        ) {
            Navigation(authState)
        }
    }
}
