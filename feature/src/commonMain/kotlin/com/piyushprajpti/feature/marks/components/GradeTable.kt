package com.piyushprajpti.feature.marks.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.marks.grade_card.SubjectResult

@Composable
fun GradeTable(
    subjects: List<SubjectResult>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(CustomTheme.colors.secondaryBackground)
    ) {
        // Table Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(CustomTheme.colors.primary.copy(alpha = 0.1f))
                .padding(vertical = 12.dp, horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "SUBJECT", modifier = Modifier.weight(3f), fontSize = 10.sp, fontWeight = FontWeight.Bold, color = CustomTheme.colors.tertiaryText)
            Text(text = "CR", modifier = Modifier.weight(1f), fontSize = 10.sp, fontWeight = FontWeight.Bold, color = CustomTheme.colors.tertiaryText, textAlign = TextAlign.Center)
            Text(text = "INT", modifier = Modifier.weight(1f), fontSize = 10.sp, fontWeight = FontWeight.Bold, color = CustomTheme.colors.tertiaryText, textAlign = TextAlign.Center)
            Text(text = "EXT", modifier = Modifier.weight(1f), fontSize = 10.sp, fontWeight = FontWeight.Bold, color = CustomTheme.colors.tertiaryText, textAlign = TextAlign.Center)
            Text(text = "TOT", modifier = Modifier.weight(1f), fontSize = 10.sp, fontWeight = FontWeight.Bold, color = CustomTheme.colors.tertiaryText, textAlign = TextAlign.Center)
            Text(text = "GRD", modifier = Modifier.weight(1f), fontSize = 10.sp, fontWeight = FontWeight.Bold, color = CustomTheme.colors.tertiaryText, textAlign = TextAlign.Center)
        }

        subjects.forEachIndexed { index, subject ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp, horizontal = 15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = subject.name,
                    modifier = Modifier.weight(3f),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = CustomTheme.colors.primaryText
                )
                Text(
                    text = subject.credit.toString(),
                    modifier = Modifier.weight(1f),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = CustomTheme.colors.secondaryText,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = subject.internal.toString(),
                    modifier = Modifier.weight(1f),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = CustomTheme.colors.secondaryText,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = subject.external.toString(),
                    modifier = Modifier.weight(1f),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = CustomTheme.colors.secondaryText,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = subject.total.toString(),
                    modifier = Modifier.weight(1f),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = CustomTheme.colors.secondaryText,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = subject.grade,
                    modifier = Modifier.weight(1f),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = CustomTheme.colors.primary,
                    textAlign = TextAlign.Center
                )
            }
            if (index < subjects.size - 1) {
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 15.dp),
                    thickness = 0.5.dp,
                    color = CustomTheme.colors.outline.copy(alpha = 0.3f)
                )
            }
        }
    }
}
