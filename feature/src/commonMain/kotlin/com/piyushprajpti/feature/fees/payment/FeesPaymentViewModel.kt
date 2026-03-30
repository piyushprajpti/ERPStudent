package com.piyushprajpti.feature.fees.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class FeesPaymentViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(FeesPaymentState())
    val state = _state.onStart {
        loadInitialData()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = FeesPaymentState()
    )

    private fun loadInitialData() {
        _state.update {
            it.copy(
                studentName = "Arjun Mehta",
                studentId = "2024CS42",
                semester = "Semester 4",
                feeHeads = listOf(
                    FeeHead("1", "Tuition Fee", 1000.0, true),
                    FeeHead("2", "Library Fee", 50.0, false),
                    FeeHead("3", "Hostel Fee", 500.0, true),
                    FeeHead("4", "Examination Fee", 200.0, false),
                    FeeHead("5", "Lab Fee", 150.0, false)
                ),
                paymentMethods = listOf(
                    PaymentMethod("upi", "UPI", "ic_upi"),
                    PaymentMethod("netbanking", "Net Banking", "ic_netbanking"),
                    PaymentMethod("debit", "Debit Card", "ic_debit_card"),
                    PaymentMethod("credit", "Credit Card", "ic_credit_card")
                )
            )
        }
    }

    fun onEvent(event: FeesPaymentEvent) {
        when (event) {
            is FeesPaymentEvent.ToggleFeeHead -> {
                _state.update { state ->
                    val updatedHeads = state.feeHeads.map {
                        if (it.id == event.id) it.copy(isSelected = !it.isSelected) else it
                    }
                    state.copy(feeHeads = updatedHeads)
                }
            }
            is FeesPaymentEvent.SelectFeesPaymentMethod -> {
                _state.update { it.copy(selectedPaymentMethodId = event.id) }
            }
            FeesPaymentEvent.ReviewFeesPayment -> {
                if (state.value.selectedFeeHeads.isEmpty()) {
                    messageHelper.showError("Please select at least one fee head")
                    return
                }
                if (state.value.selectedPaymentMethodId == null) {
                    messageHelper.showError("Please select a payment method")
                    return
                }
                _state.update { it.copy(overlayType = PaymentOverlayType.Confirmation) }
            }
            FeesPaymentEvent.DismissOverlay -> {
                _state.update { it.copy(overlayType = null) }
            }
            FeesPaymentEvent.ProceedToPay -> {
                _state.update {
                    it.copy(
                        overlayType = PaymentOverlayType.Success,
                        receiptInfo = ReceiptInfo(
                            receiptNo = "#ARJ-PAY-88231",
                            dateAndTime = "Oct 24, 2024 • 14:30",
                            paidVia = "UPI (Arjun@okhdfc)"
                        )
                    )
                }
            }
            FeesPaymentEvent.DownloadReceipt -> {
                messageHelper.showMessage("Receipt downloaded successfully")
            }
            FeesPaymentEvent.GoToHome -> {
                // Navigation handled in Screen
            }
        }
    }
}
