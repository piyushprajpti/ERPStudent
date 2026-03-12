package com.onusant.learnohub.utils.message_helper

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.piyushprajpti.feature.utils.message_helper.model.Alert
import com.piyushprajpti.feature.utils.message_helper.model.Toast

class MessageHelper {

    private var _state = mutableStateOf(MessageState())
    val state: State<MessageState> = _state

    fun setAlert(alert: Alert?) {
        _state.value = state.value.copy(
            alert = alert
        )
    }

    fun setToast(toast: Toast?) {
        _state.value = state.value.copy(
            toast = toast
        )
    }

}