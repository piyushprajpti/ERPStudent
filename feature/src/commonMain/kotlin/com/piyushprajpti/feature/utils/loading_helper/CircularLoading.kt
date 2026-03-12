package com.piyushprajpti.feature.utils.loading_helper

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.piyushprajpti.college_erp_system.theme.CustomTheme

@Composable
fun CircularLoading(size: Dp) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(0.1f)),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(size),
            color = CustomTheme.colors.white,
            strokeCap = StrokeCap.Round,
            strokeWidth = 4.dp
        )
    }
}