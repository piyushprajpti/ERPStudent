package com.piyushprajpti.erpstudent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.piyushprajpti.college_erp_system.theme.AppTheme
import com.piyushprajpti.college_erp_system.theme.CustomTheme

@Composable
@Preview
fun App() {
    AppTheme {
//        val uiSource = viewModel.state.collectAsState()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(CustomTheme.colors.primaryBack),
            contentAlignment = Alignment.Center
        ) {
//            if (!uiSource.value.isLoading) {
                Navigation(null)
//            } else {
//                CircularProgressIndicator()
//            }
        }
    }
}