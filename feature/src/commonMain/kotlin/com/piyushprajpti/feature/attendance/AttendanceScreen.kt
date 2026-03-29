package com.piyushprajpti.feature.attendance

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.attendance.components.AttendanceSummaryCard
import com.piyushprajpti.feature.attendance.components.OverallAttendanceCircular
import com.piyushprajpti.feature.attendance.components.SubjectAttendanceCard
import com.piyushprajpti.feature.components.LocalBottomBarPadding
import com.piyushprajpti.feature.components.loading_helper.LoadingWidget
import com.piyushprajpti.feature.components.message_helper.composables.AlertDialog
import com.piyushprajpti.feature.components.message_helper.composables.ToastWidget
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AttendanceScreen(
    viewModel: AttendanceViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val topBarPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val bottomBarPadding = LocalBottomBarPadding.current

    LoadingWidget(viewModel.loadingHelper.state.value)
    AlertDialog(viewModel.messageHelper)
    ToastWidget(viewModel.messageHelper)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.primaryBackground),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(
            top = 15.dp + topBarPadding,
            bottom = bottomBarPadding,
            start = 15.dp,
            end = 15.dp
        )
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(32.dp))
                    .background(CustomTheme.colors.secondaryBackground)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OverallAttendanceCircular(
                    percentage = state.overallAttendance
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    AttendanceSummaryCard(
                        count = state.totalPresent,
                        label = "Present",
                        color = CustomTheme.colors.success,
                        modifier = Modifier.weight(1f)
                    )
                    AttendanceSummaryCard(
                        count = state.totalAbsent,
                        label = "Absent",
                        color = CustomTheme.colors.error,
                        modifier = Modifier.weight(1f)
                    )
                    AttendanceSummaryCard(
                        count = state.totalLate,
                        label = "Late",
                        color = CustomTheme.colors.warning,
                        modifier = Modifier.weight(1f)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(CustomTheme.colors.primary.copy(alpha = 0.05f))
                        .border(1.dp, CustomTheme.colors.primary.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ErrorOutline,
                        contentDescription = null,
                        tint = CustomTheme.colors.primary,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Note: A minimum of ${state.minAttendanceRequirement.toInt()}% attendance is required across all subjects.",
                        color = CustomTheme.colors.primary.copy(alpha = 0.8f),
                        fontSize = 12.sp,
                        lineHeight = 16.sp
                    )
                }
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Subjects",
                    fontWeight = FontWeight.SemiBold,
                    color = CustomTheme.colors.primaryText,
                    fontSize = 18.sp
                )

                Text(
                    text = state.semester.uppercase(),
                    color = CustomTheme.colors.secondaryText,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 1.sp
                )
            }
        }

        items(state.subjectAttendanceList) { subject ->
            SubjectAttendanceCard(
                subject = subject,
                attendanceRecords = state.attendanceRecords,
                minRequirement = state.minAttendanceRequirement
            )
        }
    }
}
