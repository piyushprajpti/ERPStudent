package com.piyushprajpti.feature.attendance.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.piyushprajpti.college_erp_system.theme.CustomTheme
@Composable
fun getStatusColor(status: AttendanceStatus): Color {
    return when (status) {
        AttendanceStatus.PRESENT -> CustomTheme.colors.success
        AttendanceStatus.ABSENT -> CustomTheme.colors.error
        AttendanceStatus.LATE -> CustomTheme.colors.warning
        AttendanceStatus.NO_CLASS -> CustomTheme.colors.tertiaryText
    }
}
