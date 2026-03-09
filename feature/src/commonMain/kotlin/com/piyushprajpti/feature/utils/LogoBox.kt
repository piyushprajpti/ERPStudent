package com.piyushprajpti.feature.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.piyushprajpti.college_erp_system.theme.CustomTheme

@Composable
fun LogoBox(
    modifier: Modifier = Modifier,
    imageUrl: String?
) {
    if (imageUrl.isNullOrBlank()) {
        Icon(
            imageVector = Icons.Default.School,
            contentDescription = "logo",
            modifier = modifier.padding(5.dp),
            tint = CustomTheme.colors.primaryText
        )
    } else {
        AsyncImage(
            model = imageUrl,
            contentDescription = "logo",
            modifier = modifier,
            contentScale = ContentScale.Crop
        )
    }
}
