package com.piyushprajpti.feature.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme

@Composable
fun SearchBox(
    modifier: Modifier = Modifier,
    placeholderText: String,
    value: String,
    onValueChange: (String) -> Unit,
    onClearClick: () -> Unit
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = placeholderText,
                fontSize = 14.sp,
                color = CustomTheme.colors.secondaryText
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "search",
                tint = CustomTheme.colors.darkGray
            )
        },
        trailingIcon = {
            if (value.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "clear",
                    tint = CustomTheme.colors.darkGray,
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable { onClearClick() }
                        .padding(5.dp)
                )
            }
        },
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = CustomTheme.colors.secondaryBack,
            unfocusedContainerColor = CustomTheme.colors.secondaryBack,

            focusedBorderColor = CustomTheme.colors.primary.copy(alpha = 0.5f),
            unfocusedBorderColor = CustomTheme.colors.lightGray,

            focusedTextColor = CustomTheme.colors.primaryText,
            unfocusedTextColor = CustomTheme.colors.secondaryText,
            cursorColor = CustomTheme.colors.primary,

            selectionColors = TextSelectionColors(
                handleColor = CustomTheme.colors.primary,
                backgroundColor = CustomTheme.colors.primary.copy(0.3f)
            )
        )
    )
}