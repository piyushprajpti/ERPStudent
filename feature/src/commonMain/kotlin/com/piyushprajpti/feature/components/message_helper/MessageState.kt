package com.piyushprajpti.feature.components.message_helper

import com.piyushprajpti.feature.components.message_helper.model.Alert
import com.piyushprajpti.feature.components.message_helper.model.Toast

data class MessageState(
    val alert: Alert? = null,
    val toast: Toast? = null
)