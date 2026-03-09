package com.piyushprajpti.feature.utils

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme

@Composable
fun PrimaryActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    enabled: Boolean = true,
    containerColor: Color = CustomTheme.colors.primary,
    contentColor: Color = CustomTheme.colors.primaryText,
    disabledContainerColor: Color = containerColor.copy(alpha = 0.5f),
    disabledContentColor: Color = contentColor.copy(alpha = 0.7f),
    shape: Shape = RoundedCornerShape(12.dp),
    height: Dp = 50.dp,
    content: @Composable (RowScope.() -> Unit)? = null
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        enabled = enabled && !isLoading,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor
        ),
        shape = shape
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.height(height / 2),
                color = contentColor,
                strokeWidth = 2.dp
            )
        } else {
            if (content != null) {
                content()
            } else {
                Text(
                    text = text,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
    }
}
