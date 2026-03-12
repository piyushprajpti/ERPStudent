package com.piyushprajpti.feature.utils.message_helper.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.onusant.learnohub.utils.message_helper.MessageHelper
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.utils.message_helper.model.Toast
import com.piyushprajpti.feature.utils.message_helper.model.ToastMessage
import org.jetbrains.compose.resources.stringResource

@Composable
fun ToastWidget(messageHelper: MessageHelper) {
    ToastWidgetBase(messageHelper.state.value.toast) {
        messageHelper.setToast(null)
    }
}

@Composable
private fun ToastWidgetBase(
    toast: Toast? = null,
    onDismissRequest: () -> Unit
) {
    val text =
        if (toast != null) {
            when (toast.text) {
                is ToastMessage.String -> toast.text.value
                is ToastMessage.StringRes -> stringResource(toast.text.value)
            }
        } else return onDismissRequest()

    val color = if (toast.isError) CustomTheme.colors.errorRed else CustomTheme.colors.successGreen

    Popup(
        alignment = Alignment.TopCenter,
        onDismissRequest = onDismissRequest,
        properties = PopupProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Text(
            text,
            Modifier
                .padding(15.dp)
                .background(color, CircleShape)
                .padding(horizontal = 30.dp, vertical = 15.dp),
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 14.sp,
            lineHeight = 14.sp
        )
    }
}