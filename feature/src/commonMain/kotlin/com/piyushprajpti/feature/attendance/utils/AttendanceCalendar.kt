package com.piyushprajpti.feature.attendance.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.number
import kotlinx.datetime.plus

@Composable
fun AttendanceCalendar(
    selectedDate: LocalDate,
    attendanceRecords: Map<LocalDate, AttendanceStatus>,
    modifier: Modifier = Modifier
) {
    var displayedMonth by remember {
        mutableStateOf(LocalDate(selectedDate.year, selectedDate.month.number, 1))
    }

    LaunchedEffect(selectedDate.year, selectedDate.month.number) {
        displayedMonth = LocalDate(selectedDate.year, selectedDate.month.number, 1)
    }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val monthTitle = displayedMonth.month.name
                .lowercase()
                .replaceFirstChar { it.uppercase() }
            Text(
                text = "$monthTitle ${displayedMonth.year}",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = CustomTheme.colors.primaryText
            )
            Row {
                IconButton(
                    onClick = { displayedMonth = displayedMonth.minus(DatePeriod(months = 1)) }) {
                    Icon(
                        Icons.Default.ArrowBackIosNew,
                        contentDescription = null,
                        tint = CustomTheme.colors.secondaryText,
                        modifier = Modifier
                            .size(25.dp)
                            .clip(CircleShape)
                            .background(CustomTheme.colors.primaryBackground)
                            .padding(6.dp)
                    )
                }
                IconButton(
                    onClick = { displayedMonth = displayedMonth.plus(DatePeriod(months = 1)) }
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowForwardIos,
                        contentDescription = null,
                        tint = CustomTheme.colors.secondaryText,
                        modifier = Modifier
                            .size(25.dp)
                            .clip(CircleShape)
                            .background(CustomTheme.colors.primaryBackground)
                            .padding(6.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            val days = listOf("SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT")
            days.forEach { day ->
                Text(
                    text = day,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = CustomTheme.colors.secondaryText
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        val daysInMonth = LocalDate(displayedMonth.year, displayedMonth.monthNumber, 1)
            .plus(DatePeriod(months = 1, days = -1))
            .dayOfMonth
        val leadingEmptyDays = (displayedMonth.dayOfWeek.ordinal + 1) % 7

        val calendarCells = buildList<LocalDate?> {
            repeat(leadingEmptyDays) { add(null) }
            (1..daysInMonth).forEach { day ->
                add(LocalDate(displayedMonth.year, displayedMonth.monthNumber, day))
            }

            val trailingEmptyDays = (7 - (size % 7)) % 7
            repeat(trailingEmptyDays) { add(null) }
        }

        val rows = calendarCells.chunked(7)

        rows.forEach { week ->
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                week.forEach { date ->
                    val isSelected = date == selectedDate
                    val status = date?.let { attendanceRecords[it] }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        if (date != null) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = date.dayOfMonth.toString(),
                                    color = if (isSelected) CustomTheme.colors.primaryText else CustomTheme.colors.secondaryText,
                                    fontSize = 12.sp,
                                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                                    modifier = if (isSelected) {
                                        Modifier
                                            .clip(CircleShape)
                                            .background(CustomTheme.colors.primary)
                                            .padding(8.dp)
                                    } else {
                                        Modifier
                                    }
                                )
                                if (status != null) {
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Box(
                                        modifier = Modifier
                                            .size(4.dp)
                                            .clip(CircleShape)
                                            .background(getStatusColor(status))
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AttendanceIndicator(label = "PRESENT", color = CustomTheme.colors.success)
            AttendanceIndicator(label = "ABSENT", color = CustomTheme.colors.error)
            AttendanceIndicator(label = "LATE", color = CustomTheme.colors.warning)
            AttendanceIndicator(label = "NO CLASS", color = CustomTheme.colors.tertiaryText)
        }
    }
}

@Composable
private fun AttendanceIndicator(
    label: String,
    color: Color
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(color)
        )
        Text(
            text = label,
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            color = CustomTheme.colors.secondaryText
        )
    }
}
