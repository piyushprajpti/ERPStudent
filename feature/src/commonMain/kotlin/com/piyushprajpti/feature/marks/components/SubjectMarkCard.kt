package com.piyushprajpti.feature.marks.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.marks.overview.Subject

@Composable
fun SubjectMarkCard(
    subject: Subject,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = subject.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = CustomTheme.colors.primaryText
                )
                Text(
                    text = "${subject.code} • ${subject.professor}",
                    fontSize = 12.sp,
                    color = CustomTheme.colors.secondaryText
                )
            }
            
            val gradeColor = when (subject.grade) {
                "A+", "A" -> CustomTheme.colors.success
                "B" -> CustomTheme.colors.blue
                "C" -> CustomTheme.colors.warning
                else -> CustomTheme.colors.error
            }
            
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(gradeColor.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = subject.grade,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = gradeColor
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            MarkBox(
                label = "MIDTERM",
                score = "${subject.midtermMarks.score}/${subject.midtermMarks.total}",
                modifier = Modifier.weight(1f)
            )
            MarkBox(
                label = "ASSIGN.",
                score = "${subject.assignmentMarks.score}/${subject.assignmentMarks.total}",
                modifier = Modifier.weight(1f)
            )
            MarkBox(
                label = "END-TERM",
                score = "${subject.endTermMarks.score}/${subject.endTermMarks.total}",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun MarkBox(
    label: String,
    score: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(CustomTheme.colors.outline.copy(alpha = 0.1f))
            .padding(vertical = 10.dp, horizontal = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = label,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            color = CustomTheme.colors.tertiaryText
        )
        Text(
            text = score,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = CustomTheme.colors.primaryText
        )
    }
}
