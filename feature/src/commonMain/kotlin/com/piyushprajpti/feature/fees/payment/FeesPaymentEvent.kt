package com.piyushprajpti.feature.fees.payment

sealed interface FeesPaymentEvent {
    data class ToggleFeeHead(val id: String) : FeesPaymentEvent
    data class SelectFeesPaymentMethod(val id: String) : FeesPaymentEvent
    data object ReviewFeesPayment : FeesPaymentEvent
    data object DismissOverlay : FeesPaymentEvent
    data object ProceedToPay : FeesPaymentEvent
    data object DownloadReceipt : FeesPaymentEvent
    data object GoToHome : FeesPaymentEvent
}
