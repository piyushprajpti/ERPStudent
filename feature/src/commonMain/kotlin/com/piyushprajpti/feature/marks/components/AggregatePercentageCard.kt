package com.piyushprajpti.feature.marks.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import kotlin.math.roundToInt

@Composable
fun AggregatePercentageCard(
    percentage: Float,
    onViewGradeCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(CustomTheme.colors.primary)
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = "AGGREGATE PERCENTAGE",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.white.copy(alpha = 0.8f)
            )
            Text(
                text = "${(percentage * 10).roundToInt() / 10.0}%",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.white
            )
        }

        Button(
            onClick = onViewGradeCardClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = CustomTheme.colors.white,
                contentColor = CustomTheme.colors.primary
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.padding(start = 10.dp)
        ) {
            Text(
                text = "View Grade Card",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
