package com.piyushprajpti.feature.utils.message_helper.model

import org.jetbrains.compose.resources.StringResource

sealed interface ToastMessage {
    data class StringRes(val value: StringResource) : ToastMessage
    data class String(val value: kotlin.String) : ToastMessage
}

data class Toast(
    val isError: Boolean = false,
    val text: ToastMessage = ToastMessage.String("")
)
