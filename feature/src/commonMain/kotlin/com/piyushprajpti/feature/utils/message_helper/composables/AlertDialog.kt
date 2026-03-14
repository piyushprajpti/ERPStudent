package com.piyushprajpti.feature.utils.message_helper.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.compose.ui.zIndex
import com.onusant.learnohub.utils.message_helper.MessageHelper
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.utils.message_helper.model.Alert

@Composable
fun AlertDialog(messageHelper: MessageHelper) {
    AlertDialogBase(messageHelper.state.value.alert) {
        messageHelper.setAlert(null)
    }
}

@Composable
fun AlertDialogBase(alert: Alert?, onDismissRequest: () -> Unit) {
    if (alert == null) return
    val label = alert.label
    val message = alert.message

    Surface(
        Modifier
            .fillMaxSize()
            .zIndex(10f),
        color = Color(0x99000000)
    ) {
        Popup(
            alignment = Alignment.Center,
            onDismissRequest = onDismissRequest,
            properties = PopupProperties(
                dismissOnBackPress = alert.cancelable,
                dismissOnClickOutside = alert.cancelable
            )
        ) {
            Column(
                Modifier
                    .padding(horizontal = 30.dp)
                    .width(IntrinsicSize.Max)
                    .widthIn(min = 230.dp)
                    .background(Color.White, RoundedCornerShape(10.dp))
            ) {
                Text(
                    text = label,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color(0xFFEFEFEF))
                )
                Text(
                    text = message,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.DarkGray
                )
                if (alert.requireAction) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Max)
                            .clip(
                                RoundedCornerShape(
                                    bottomStart = 10.dp,
                                    bottomEnd = 10.dp
                                )
                            )
                    ) {
                        Text(
                            text = "Cancel",
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .clip(
                                    RoundedCornerShape(
                                        bottomStart = 10.dp,
                                    )
                                )
                                .background(Color(0xFFEFEFEF))
                                .clickable {
                                    alert.negativeAction()
                                    onDismissRequest()
                                }
                                .padding(15.dp),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Medium,
                            lineHeight = 16.sp,
                            color = Color.Black
                        )
                        Text(
                            text = "Confirm",
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .clip(
                                    RoundedCornerShape(
                                        bottomEnd = 10.dp
                                    )
                                )
                                .background(CustomTheme.colors.primary)
                                .clickable {
                                    alert.positiveAction()
                                    onDismissRequest()
                                }
                                .padding(15.dp),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Medium,
                            lineHeight = 16.sp,
                            color = Color.White
                        )
                    }
                } else {
                    Text(
                        text = "Ok",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(
                                RoundedCornerShape(
                                    bottomStart = 10.dp,
                                    bottomEnd = 10.dp
                                )
                            )
                            .background(Color(0xFFEFEFEF))
                            .clickable {
                                alert.neutralAction()
                                onDismissRequest()
                            }
                            .padding(15.dp),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Black,
                        color = Color.Black
                    )
                }
            }
        }
    }
}
