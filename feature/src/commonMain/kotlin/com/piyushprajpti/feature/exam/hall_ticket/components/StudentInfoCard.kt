package com.piyushprajpti.feature.exam.hall_ticket.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.exam.hall_ticket.ExamHallTicketState

@Composable
fun StudentInfoCard(
    state: ExamHallTicketState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(25.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        CustomTheme.colors.secondaryBackground,
                        CustomTheme.colors.primary.copy(alpha = 0.1f)
                    )
                )
            )
            .padding(20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
                    .background(CustomTheme.colors.white)
                    .border(2.dp, CustomTheme.colors.outline, CircleShape)
            ) {
                AsyncImage(
                    model = state.profileImage,
                    contentDescription = "Profile Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .clip(CircleShape)
                        .background(CustomTheme.colors.success)
                        .border(2.dp, CustomTheme.colors.white, CircleShape)
                        .align(Alignment.BottomEnd)
                        .offset(x = (-2).dp, y = (-2).dp)
                )
            }

            Spacer(modifier = Modifier.width(20.dp))

            Column {
                Text(
                    text = state.studentName,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = CustomTheme.colors.primaryText
                )
                Text(
                    text = "ID: ${state.studentId}",
                    fontSize = 14.sp,
                    color = CustomTheme.colors.secondaryText
                )

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(15.dp))
                        .background(CustomTheme.colors.primary.copy(alpha = 0.15f))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = state.course,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = CustomTheme.colors.primary
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "${state.semester} • Academic Year",
                    fontSize = 13.sp,
                    color = CustomTheme.colors.tertiaryText
                )
                Text(
                    text = state.academicYear,
                    fontSize = 13.sp,
                    color = CustomTheme.colors.tertiaryText
                )
            }
        }
    }
}
