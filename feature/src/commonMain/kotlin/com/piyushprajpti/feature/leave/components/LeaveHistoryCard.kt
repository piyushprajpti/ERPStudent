package com.piyushprajpti.feature.leave.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.leave.leave_history.LeaveHistory
import com.piyushprajpti.feature.leave.leave_history.LeaveStatus

@Composable
fun LeaveHistoryCard(
    leave: LeaveHistory,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .padding(15.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "SUBMITTED ON ${leave.submittedDate.uppercase()}",
                    fontSize = 11.sp,
                    color = CustomTheme.colors.tertiaryText,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = leave.type,
                    fontSize = 18.sp,
                    color = CustomTheme.colors.primaryText,
                    fontWeight = FontWeight.Bold
                )
            }
            
            StatusBadge(status = leave.status)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Duration",
                    fontSize = 12.sp,
                    color = CustomTheme.colors.tertiaryText
                )
                Text(
                    text = "${leave.startDate} - ${leave.endDate}",
                    fontSize = 14.sp,
                    color = CustomTheme.colors.primaryText,
                    fontWeight = FontWeight.SemiBold
                )
            }
            
            Spacer(
                modifier = Modifier
                    .width(1.dp)
                    .height(35.dp)
                    .background(CustomTheme.colors.outline)
            )
            
            Column(modifier = Modifier.weight(1f).padding(start = 15.dp)) {
                Text(
                    text = "Total",
                    fontSize = 12.sp,
                    color = CustomTheme.colors.tertiaryText
                )
                Text(
                    text = "${leave.totalDays} ${if (leave.totalDays > 1) "Days" else "Day"}",
                    fontSize = 14.sp,
                    color = CustomTheme.colors.primaryText,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.height(15.dp))
        HorizontalDivider(color = CustomTheme.colors.outline)
        Spacer(modifier = Modifier.height(15.dp))

        if (leave.status == LeaveStatus.Rejected && leave.rejectionRemark != null) {
            Text(
                text = "Rejection Remark:",
                fontSize = 12.sp,
                color = CustomTheme.colors.tertiaryText
            )
            Text(
                text = leave.rejectionRemark,
                fontSize = 13.sp,
                color = CustomTheme.colors.secondaryText,
                lineHeight = 18.sp
            )
        } else {
            Text(
                text = "Reason:",
                fontSize = 12.sp,
                color = CustomTheme.colors.tertiaryText
            )
            Text(
                text = leave.reason,
                fontSize = 13.sp,
                color = CustomTheme.colors.secondaryText,
                lineHeight = 18.sp
            )
        }
    }
}

@Composable
private fun StatusBadge(status: LeaveStatus) {
    val (backgroundColor, textColor) = when (status) {
        LeaveStatus.Pending -> CustomTheme.colors.warning.copy(alpha = 0.1f) to CustomTheme.colors.warning
        LeaveStatus.Approved -> CustomTheme.colors.success.copy(alpha = 0.1f) to CustomTheme.colors.success
        LeaveStatus.Rejected -> CustomTheme.colors.error.copy(alpha = 0.1f) to CustomTheme.colors.error
        else -> CustomTheme.colors.outline to CustomTheme.colors.primaryText
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50.dp))
            .background(backgroundColor)
            .padding(horizontal = 12.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = status.label.uppercase(),
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
    }
}
