package com.piyushprajpti.feature.utils.message_helper

import com.piyushprajpti.feature.utils.message_helper.model.Alert
import com.piyushprajpti.feature.utils.message_helper.model.Toast

data class MessageState(
    val alert: Alert? = null,
    val toast: Toast? = null
)