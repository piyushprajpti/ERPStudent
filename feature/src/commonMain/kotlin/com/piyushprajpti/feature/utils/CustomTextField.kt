package com.piyushprajpti.feature.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String? = null,
    placeholder: String,
    columnModifier: Modifier = Modifier,
    textFieldModifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    errorMessage: String? = null,
    enabled: Boolean = true,
    singleLine: Boolean = true
) {
    Column(modifier = columnModifier.fillMaxWidth()) {
        if (label != null) {
            Text(
                text = label,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = if (enabled) CustomTheme.colors.secondaryText else CustomTheme.colors.mediumGray
            )
            Spacer(modifier = Modifier.height(5.dp))
        }

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = textFieldModifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = placeholder,
                    color = CustomTheme.colors.mediumGray
                )
            },
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            singleLine = singleLine,
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = CustomTheme.colors.secondaryBack,
                unfocusedContainerColor = CustomTheme.colors.secondaryBack,
                disabledContainerColor = CustomTheme.colors.lightGray.copy(alpha = 0.3f),

                focusedBorderColor = CustomTheme.colors.primary.copy(alpha = 0.5f),
                unfocusedBorderColor = CustomTheme.colors.lightGray,
                disabledBorderColor = CustomTheme.colors.lightGray.copy(alpha = 0.5f),

                focusedTextColor = CustomTheme.colors.primaryText,
                unfocusedTextColor = CustomTheme.colors.secondaryText,
                disabledTextColor = CustomTheme.colors.mediumGray,
                
                cursorColor = CustomTheme.colors.primary,

                selectionColors = TextSelectionColors(
                    handleColor = CustomTheme.colors.primary,
                    backgroundColor = CustomTheme.colors.primary.copy(0.3f)
                ),
                disabledPlaceholderColor = CustomTheme.colors.mediumGray.copy(alpha = 0.5f),
                disabledLabelColor = CustomTheme.colors.mediumGray
            ),
            isError = isError,
            enabled = enabled
        )
        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                color = CustomTheme.colors.errorRed,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp, start = 4.dp)
            )
        }
    }
}
