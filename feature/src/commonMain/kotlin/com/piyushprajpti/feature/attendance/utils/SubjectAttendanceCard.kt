package com.piyushprajpti.feature.attendance.utils

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import kotlinx.datetime.LocalDate

@Composable
fun SubjectAttendanceCard(
    subject: SubjectAttendance,
    attendanceRecords: Map<LocalDate, AttendanceStatus>,
    minRequirement: Float,
    modifier: Modifier = Modifier
) {
    var isCalendarVisible by remember { mutableStateOf(false) }

    val color = when {
        subject.percentage >= minRequirement -> CustomTheme.colors.success
        else -> CustomTheme.colors.error
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = subject.subjectName,
                    color = CustomTheme.colors.primaryText,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
                Text(
                    text = "${subject.courseCode} • ${subject.professorName}",
                    color = CustomTheme.colors.secondaryText,
                    fontSize = 13.sp
                )
            }
            Text(
                text = "${subject.percentage.toInt()}%",
                color = color,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LinearProgressIndicator(
            progress = { subject.percentage / 100f },
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
                .clip(CircleShape),
            color = color,
            trackColor = CustomTheme.colors.primaryBackground,
            strokeCap = StrokeCap.Round
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(color.copy(alpha = 0.1f))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "${subject.attendedLectures}/${subject.totalLectures} ATTENDED",
                    color = color,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { isCalendarVisible = !isCalendarVisible }
            ) {
                Text(
                    text = "VIEW CALENDAR",
                    color = CustomTheme.colors.primary.copy(alpha = 0.9f),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp
                )
                Icon(
                    imageVector = if (isCalendarVisible) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = CustomTheme.colors.primary.copy(alpha = 0.9f),
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        AnimatedVisibility(visible = isCalendarVisible) {
            Column {
                Spacer(modifier = Modifier.height(20.dp))
                AttendanceCalendar(
                    selectedDate = LocalDate(2026,3,28),
                    attendanceRecords = attendanceRecords,
                )
            }
        }
    }
}
