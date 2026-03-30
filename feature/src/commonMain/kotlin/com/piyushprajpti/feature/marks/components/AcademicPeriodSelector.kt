package com.piyushprajpti.feature.marks.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.marks.overview.AcademicPeriod

@Composable
fun AcademicPeriodSelector(
    selectedPeriod: AcademicPeriod?,
    academicPeriods: List<AcademicPeriod>,
    onPeriodSelected: (AcademicPeriod) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "ACADEMIC PERIOD",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = CustomTheme.colors.secondaryText
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, CustomTheme.colors.outline, RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp))
                .background(CustomTheme.colors.secondaryBackground.copy(alpha = 0.3f))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded }
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = selectedPeriod?.name ?: "Select Period",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = CustomTheme.colors.primaryText
                )

                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = CustomTheme.colors.secondaryText
                )
            }

            AnimatedVisibility(visible = expanded) {
                Column {
                    academicPeriods.forEachIndexed { index, period ->
                        HorizontalDivider(
                            modifier = Modifier.padding(horizontal = 15.dp),
                            thickness = 0.5.dp,
                            color = CustomTheme.colors.outline.copy(alpha = 0.5f)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onPeriodSelected(period)
                                    expanded = false
                                }
                                .padding(horizontal = 15.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = period.name,
                                fontSize = 14.sp,
                                color = if (period.id == selectedPeriod?.id) 
                                    CustomTheme.colors.primary 
                                else 
                                    CustomTheme.colors.primaryText,
                                fontWeight = if (period.id == selectedPeriod?.id) 
                                    FontWeight.Bold 
                                else 
                                    FontWeight.Normal
                            )

                            if (period.id == selectedPeriod?.id) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = CustomTheme.colors.primary,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
