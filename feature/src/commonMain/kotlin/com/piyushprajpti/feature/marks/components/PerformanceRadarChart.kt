package com.piyushprajpti.feature.marks.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.marks.grade_card.PerformancePoint
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun PerformanceRadarChart(
    performancePoints: List<PerformancePoint>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 20.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ShowChart,
                contentDescription = null,
                tint = CustomTheme.colors.primary,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Performance Overview",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.primaryText
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentAlignment = Alignment.Center
        ) {
            val primaryColor = CustomTheme.colors.primary
            val outlineColor = CustomTheme.colors.outline
            val textColor = CustomTheme.colors.secondaryText
            val textMeasurer = rememberTextMeasurer()

            Canvas(modifier = Modifier.fillMaxSize()) {
                val center = Offset(size.width / 2, size.height / 2)
                val radius = size.minDimension / 2 - 40.dp.toPx()
                val numPoints = performancePoints.size
                val angleStep = 2 * PI / numPoints

                // Draw Background Polygons (Web)
                for (i in 1..5) {
                    val r = radius * i / 5
                    val path = Path()
                    for (j in 0 until numPoints) {
                        val angle = j * angleStep - PI / 2
                        val x = center.x + r * cos(angle).toFloat()
                        val y = center.y + r * sin(angle).toFloat()
                        if (j == 0) path.moveTo(x, y) else path.lineTo(x, y)
                    }
                    path.close()
                    drawPath(path, outlineColor.copy(alpha = 0.2f), style = Stroke(width = 1.dp.toPx()))
                }

                // Draw Axes and Labels
                for (i in 0 until numPoints) {
                    val angle = i * angleStep - PI / 2
                    val x = center.x + radius * cos(angle).toFloat()
                    val y = center.y + radius * sin(angle).toFloat()
                    drawLine(outlineColor.copy(alpha = 0.2f), center, Offset(x, y), strokeWidth = 1.dp.toPx())

                    // Labels
                    val labelRadius = radius + 20.dp.toPx()
                    val labelX = center.x + labelRadius * cos(angle).toFloat()
                    val labelY = center.y + labelRadius * sin(angle).toFloat()
                    
                    val textLayoutResult = textMeasurer.measure(
                        text = performancePoints[i].subjectCode,
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor
                        )
                    )
                    
                    drawText(
                        textLayoutResult,
                        topLeft = Offset(labelX - textLayoutResult.size.width / 2, labelY - textLayoutResult.size.height / 2)
                    )
                }

                // Draw Data Path
                if (performancePoints.isNotEmpty()) {
                    val dataPath = Path()
                    for (i in 0 until numPoints) {
                        val angle = i * angleStep - PI / 2
                        val r = radius * performancePoints[i].score
                        val x = center.x + r * cos(angle).toFloat()
                        val y = center.y + r * sin(angle).toFloat()
                        if (i == 0) dataPath.moveTo(x, y) else dataPath.lineTo(x, y)
                    }
                    dataPath.close()
                    drawPath(dataPath, primaryColor.copy(alpha = 0.2f))
                    drawPath(dataPath, primaryColor, style = Stroke(width = 2.dp.toPx()))
                    
                    // Draw Data Points
                    for (i in 0 until numPoints) {
                        val angle = i * angleStep - PI / 2
                        val r = radius * performancePoints[i].score
                        val x = center.x + r * cos(angle).toFloat()
                        val y = center.y + r * sin(angle).toFloat()
                        drawCircle(primaryColor, radius = 4.dp.toPx(), center = Offset(x, y))
                    }
                }
            }
        }
    }
}
