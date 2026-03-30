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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.exam.schedule.ExamSummary

@Composable
fun ExamSummaryCard(
    summary: ExamSummary,
    session: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        CustomTheme.colors.primary.copy(alpha = 0.2f),
                        CustomTheme.colors.secondaryBackground
                    )
                )
            )
            .padding(20.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(CustomTheme.colors.primary)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "LIVE UPDATE",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = CustomTheme.colors.white
                    )
                }

                Text(
                    text = "Session: $session",
                    fontSize = 13.sp,
                    color = CustomTheme.colors.primary.copy(alpha = 0.8f)
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = summary.title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.primaryText,
                lineHeight = 28.sp
            )

            Spacer(modifier = Modifier.height(15.dp))

            Row {
                SummaryItem(label = "Total Exams", value = summary.totalExams.toString())
                Divider()
                SummaryItem(label = "Completed", value = summary.completedExams.toString())
                Divider()
                SummaryItem(label = "Remaining", value = summary.remainingExams.toString())
            }
        }
    }
}

@Composable
private fun SummaryItem(label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "$label: ",
            fontSize = 14.sp,
            color = CustomTheme.colors.secondaryText
        )
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = CustomTheme.colors.primaryText
        )
    }
}

@Composable
private fun Divider() {
    Text(
        text = " | ",
        fontSize = 14.sp,
        color = CustomTheme.colors.tertiaryText,
        modifier = Modifier.padding(horizontal = 4.dp)
    )
}
