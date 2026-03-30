package com.piyushprajpti.feature.exam.hall_ticket.components

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.exam.hall_ticket.HallTicketSchedule

@Composable
fun HallTicketScheduleCard(
    schedule: HallTicketSchedule,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Date Box
        Box(
            modifier = Modifier
                .size(width = 60.dp, height = 70.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(CustomTheme.colors.primaryBackground.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = schedule.month,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = CustomTheme.colors.primary
                )
                Text(
                    text = schedule.day,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = CustomTheme.colors.primaryText
                )
            }
        }

        Spacer(modifier = Modifier.width(15.dp))

        // Subject Info
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = schedule.subject,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.primaryText
            )
            
            Spacer(modifier = Modifier.height(6.dp))
            
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Outlined.Schedule,
                    contentDescription = null,
                    tint = CustomTheme.colors.tertiaryText,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = schedule.time,
                    fontSize = 13.sp,
                    color = CustomTheme.colors.secondaryText
                )
                Spacer(modifier = Modifier.width(12.dp))
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = null,
                    tint = CustomTheme.colors.tertiaryText,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = schedule.location,
                    fontSize = 13.sp,
                    color = CustomTheme.colors.secondaryText
                )
            }
        }

        // Seat Info
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "SEAT",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.tertiaryText
            )
            Text(
                text = schedule.seatNo,
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                color = CustomTheme.colors.primaryText
            )
        }
    }
}
