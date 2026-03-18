package com.piyushprajpti.feature.schedule

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.piyushprajpti.college_erp_system.theme.CustomTheme

@Composable
fun ScheduleScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Schedule Screen", color = CustomTheme.colors.primaryText)
    }
}
