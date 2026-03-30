package com.piyushprajpti.feature.exam.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.exam.schedule.Exam

@Composable
fun ExamCard(
    exam: Exam,
    modifier: Modifier = Modifier
) {
    if (exam.isToday) {
        TodayExamCard(exam, modifier)
    } else {
        UpcomingExamCard(exam, modifier)
    }
}

@Composable
private fun TodayExamCard(
    exam: Exam,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "TODAY",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.primary,
                letterSpacing = 1.sp
            )

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(CustomTheme.colors.primary.copy(alpha = 0.15f))
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(
                    text = exam.location,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = CustomTheme.colors.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = exam.title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = CustomTheme.colors.primaryText
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Info, // Placeholder for exam icon
                contentDescription = null,
                tint = CustomTheme.colors.tertiaryText,
                modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = exam.type,
                fontSize = 14.sp,
                color = CustomTheme.colors.secondaryText
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            InfoSection(
                icon = Icons.Outlined.Schedule,
                label = "TIME",
                value = exam.time,
                modifier = Modifier.weight(1f)
            )
            InfoSection(
                icon = Icons.Default.DateRange,
                label = "DATE",
                value = exam.date,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1.5f)) {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = null,
                    tint = CustomTheme.colors.secondaryText,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Invigilator: ${exam.invigilator}",
                    fontSize = 13.sp,
                    color = CustomTheme.colors.secondaryText
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                Icon(
                    imageVector = Icons.Default.Person, // Placeholder for seat icon
                    contentDescription = null,
                    tint = CustomTheme.colors.secondaryText,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Seat No: ${exam.seatNo}",
                    fontSize = 13.sp,
                    color = CustomTheme.colors.secondaryText
                )
            }
        }

        if (exam.reportingTime != null) {
            Spacer(modifier = Modifier.height(15.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(CustomTheme.colors.primaryBackground.copy(alpha = 0.5f))
                    .padding(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = null,
                        tint = CustomTheme.colors.primary,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Reporting Time: ",
                        fontSize = 14.sp,
                        color = CustomTheme.colors.secondaryText
                    )
                    Text(
                        text = exam.reportingTime,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = CustomTheme.colors.primaryText
                    )
                }
            }
        }
    }
}

@Composable
private fun UpcomingExamCard(
    exam: Exam,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .padding(15.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "UPCOMING",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.secondaryText,
                letterSpacing = 1.sp
            )

            if (exam.daysRemaining != null) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(CustomTheme.colors.outline)
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = "IN ${exam.daysRemaining} DAYS",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = CustomTheme.colors.secondaryText
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = exam.title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = CustomTheme.colors.primaryText
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconValue(icon = Icons.Outlined.Schedule, value = exam.time)
            Spacer(modifier = Modifier.width(15.dp))
            IconValue(icon = Icons.Outlined.LocationOn, value = exam.location)
            Spacer(modifier = Modifier.width(15.dp))
            IconValue(icon = Icons.Default.DateRange, value = exam.date)
        }
    }
}

@Composable
private fun InfoSection(
    icon: ImageVector,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(CustomTheme.colors.primaryBackground.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = CustomTheme.colors.primary,
                modifier = Modifier.size(18.dp)
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(
                text = label,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.tertiaryText
            )
            Text(
                text = value,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.primaryText
            )
        }
    }
}

@Composable
private fun IconValue(icon: ImageVector, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = CustomTheme.colors.secondaryText,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = value,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = CustomTheme.colors.secondaryText
        )
    }
}
