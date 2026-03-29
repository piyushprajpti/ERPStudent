package com.piyushprajpti.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Assignment
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.automirrored.outlined.LibraryBooks
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Grade
import androidx.compose.material.icons.outlined.HistoryEdu
import androidx.compose.material.icons.outlined.HomeWork
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.School
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
import com.piyushprajpti.feature.home.QuickAction

@Composable
fun QuickActionsGrid(actions: List<QuickAction>) {
    val chunks = actions.chunked(5)
    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
        chunks.forEach { rowActions ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                rowActions.forEach { action ->
                    QuickActionItem(action)
                }
                if (rowActions.size < 5) {
                    repeat(5 - rowActions.size) {
                        Spacer(modifier = Modifier.width(64.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun QuickActionItem(action: QuickAction) {
    val icon = when (action) {
        QuickAction.Notices -> Icons.Outlined.CalendarMonth
        QuickAction.Leave -> Icons.AutoMirrored.Outlined.ExitToApp
        QuickAction.Fees -> Icons.Outlined.Payments
        QuickAction.Library -> Icons.AutoMirrored.Outlined.LibraryBooks
        QuickAction.Documents -> Icons.Outlined.Description
        QuickAction.Marks -> Icons.Outlined.Grade
        QuickAction.Exam -> Icons.Outlined.HistoryEdu
        QuickAction.Hostel -> Icons.Outlined.HomeWork
        QuickAction.Events -> Icons.Outlined.Event
        QuickAction.Assignment -> Icons.AutoMirrored.Outlined.Assignment
        QuickAction.Transport -> Icons.Outlined.School
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.width(64.dp)
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(CustomTheme.colors.secondaryBackground)
                .border(1.dp, CustomTheme.colors.outline, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = action.title,
                tint = CustomTheme.colors.primaryText,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = action.title,
            color = CustomTheme.colors.secondaryText,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
