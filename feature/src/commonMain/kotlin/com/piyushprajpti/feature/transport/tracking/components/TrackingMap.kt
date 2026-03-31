package com.piyushprajpti.feature.transport.tracking.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocationSearching
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme

@Composable
fun TrackingMap(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        CustomTheme.colors.secondaryBackground,
                        CustomTheme.colors.outline
                    )
                )
            )
    ) {
        // Simulated College Marker
        MapMarker(
            modifier = Modifier.align(Alignment.Center).offset(y = (-150).dp),
            icon = Icons.Default.School,
            label = "COLLEGE",
            backgroundColor = CustomTheme.colors.primary.copy(alpha = 0.8f)
        )

        // Simulated Bus Marker
        MapMarker(
            modifier = Modifier.align(Alignment.Center).offset(y = 50.dp),
            icon = androidx.compose.material.icons.Icons.Default.School, // Should be bus, using school for now if no bus icon
            label = "ROUTE 42",
            backgroundColor = CustomTheme.colors.primary
        )

        // Zoom Controls
        Column(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 15.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            MapActionButton(icon = Icons.Default.Add) {}
            MapActionButton(icon = Icons.Default.Remove) {}
            MapActionButton(icon = Icons.Default.LocationSearching) {}
        }
    }
}

@Composable
private fun MapMarker(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(backgroundColor)
                .padding(horizontal = 8.dp, vertical = 2.dp)
        ) {
            Text(
                text = label,
                color = Color.White,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun MapActionButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = CustomTheme.colors.secondaryBackground.copy(alpha = 0.8f),
            contentColor = CustomTheme.colors.primaryText
        ),
        modifier = Modifier.size(45.dp).clip(CircleShape)
    ) {
        Icon(imageVector = icon, contentDescription = null)
    }
}
