package com.piyushprajpti.feature.utils.message_helper.model

import erpstudent.feature.generated.resources.Res
import erpstudent.feature.generated.resources.text_alert
import erpstudent.feature.generated.resources.default_alert_message
import org.jetbrains.compose.resources.StringResource

sealed interface AlertMessageType {
    data class StringRes(val value: StringResource) : AlertMessageType
    data class String(val value: kotlin.String) : AlertMessageType
}

data class Alert(
    val label: AlertMessageType = AlertMessageType.StringRes(Res.string.text_alert),
    val message: AlertMessageType = AlertMessageType.StringRes(Res.string.default_alert_message),
    var cancelable: Boolean = true,
    var requireAction: Boolean = false,
    var positiveAction: () -> Unit = {},
    var negativeAction: () -> Unit = {},
    var neutralAction: () -> Unit = {}
)
