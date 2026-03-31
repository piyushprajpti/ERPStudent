package com.piyushprajpti.feature.assignment.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.FileUpload
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.piyushprajpti.feature.assignment.dashboard.Assignment
import com.piyushprajpti.feature.assignment.dashboard.AssignmentStatus

@Composable
fun AssignmentCard(
    assignment: Assignment,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isOverdue = assignment.status == AssignmentStatus.Overdue
    val isGraded = assignment.status == AssignmentStatus.Graded
    
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .border(1.dp, CustomTheme.colors.outline, RoundedCornerShape(15.dp))
            .clickable { onClick() }
    ) {
        // Red indicator for overdue
        if (isOverdue) {
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(60.dp)
                    .clip(RoundedCornerShape(topEnd = 4.dp, bottomEnd = 4.dp))
                    .background(CustomTheme.colors.error)
                    .align(Alignment.CenterStart)
            )
        }

        Column(modifier = Modifier.padding(15.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = assignment.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = CustomTheme.colors.primaryText
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Rounded.Person,
                            contentDescription = null,
                            tint = CustomTheme.colors.tertiaryText,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Assigned by: ${assignment.assignedBy}",
                            fontSize = 12.sp,
                            color = CustomTheme.colors.secondaryText
                        )
                    }
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "WEIGHTAGE",
                        fontSize = 10.sp,
                        color = CustomTheme.colors.tertiaryText,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${assignment.weightage}%",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = CustomTheme.colors.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Rounded.CalendarMonth,
                    contentDescription = null,
                    tint = CustomTheme.colors.primary,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Due: ${assignment.dueDate}",
                    fontSize = 12.sp,
                    color = CustomTheme.colors.primary
                )
                Spacer(modifier = Modifier.width(12.dp))
                
                when (assignment.status) {
                    AssignmentStatus.Overdue -> {
                        Icon(
                            imageVector = Icons.Rounded.History,
                            contentDescription = null,
                            tint = CustomTheme.colors.error,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${assignment.daysLate} days late",
                            fontSize = 12.sp,
                            color = CustomTheme.colors.error
                        )
                    }
                    AssignmentStatus.Upcoming -> {
                        if (assignment.daysLeft != null) {
                            Icon(
                                imageVector = Icons.Rounded.Schedule,
                                contentDescription = null,
                                tint = CustomTheme.colors.secondaryText,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${assignment.daysLeft} days left",
                                fontSize = 12.sp,
                                color = CustomTheme.colors.secondaryText
                            )
                        }
                    }
                    else -> {}
                }
            }

            if (isGraded) {
                Spacer(modifier = Modifier.height(12.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(CustomTheme.colors.primary.copy(alpha = 0.05f))
                        .padding(10.dp)
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Grade: ",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = CustomTheme.colors.primaryText
                            )
                            Text(
                                text = assignment.grade ?: "N/A",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = CustomTheme.colors.primary
                            )
                        }
                        if (assignment.feedback != null) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = assignment.feedback,
                                fontSize = 11.sp,
                                color = CustomTheme.colors.secondaryText
                            )
                        }
                    }
                }
            }

            if (isOverdue || assignment.status == AssignmentStatus.Upcoming) {
                Spacer(modifier = Modifier.height(16.dp))
                if (isOverdue) {
                    Button(
                        onClick = onClick,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = CustomTheme.colors.primary),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Submit Now", fontWeight = FontWeight.Bold, color = CustomTheme.colors.white)
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(Icons.Rounded.FileUpload, contentDescription = null, modifier = Modifier.size(18.dp), tint = CustomTheme.colors.white)
                        }
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .border(1.dp, CustomTheme.colors.outline, RoundedCornerShape(12.dp))
                            .clickable { onClick() }
                            .padding(horizontal = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Submit", fontWeight = FontWeight.Bold, color = CustomTheme.colors.primaryText)
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(
                                Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                                contentDescription = null,
                                tint = CustomTheme.colors.primaryText,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
