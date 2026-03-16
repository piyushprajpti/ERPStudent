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
    labelContent: @Composable (() -> Unit)? = null,
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
        if (label != null || labelContent != null) {
            if (labelContent != null) {
                labelContent()
            } else {
                Text(
                    text = label!!,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = if (enabled) CustomTheme.colors.secondaryText else CustomTheme.colors.tertiaryText
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = textFieldModifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = placeholder,
                    color = CustomTheme.colors.tertiaryText,
                    fontSize = 14.sp
                )
            },
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            singleLine = singleLine,
            shape = RoundedCornerShape(25.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = CustomTheme.colors.secondaryBackground,
                unfocusedContainerColor = CustomTheme.colors.secondaryBackground,
                disabledContainerColor = CustomTheme.colors.secondaryBackground.copy(alpha = 0.5f),

                focusedBorderColor = CustomTheme.colors.primary.copy(alpha = 0.8f),
                unfocusedBorderColor = CustomTheme.colors.outline,
                disabledBorderColor = CustomTheme.colors.outline.copy(alpha = 0.5f),

                focusedTextColor = CustomTheme.colors.primaryText,
                unfocusedTextColor = CustomTheme.colors.primaryText,
                disabledTextColor = CustomTheme.colors.tertiaryText,
                
                cursorColor = CustomTheme.colors.primary,

                selectionColors = TextSelectionColors(
                    handleColor = CustomTheme.colors.primary,
                    backgroundColor = CustomTheme.colors.primary.copy(0.3f)
                ),
                disabledPlaceholderColor = CustomTheme.colors.tertiaryText.copy(alpha = 0.5f),
                disabledLabelColor = CustomTheme.colors.tertiaryText
            ),
            isError = isError,
            enabled = enabled
        )
        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                color = CustomTheme.colors.error,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp, start = 16.dp)
            )
        }
    }
}
