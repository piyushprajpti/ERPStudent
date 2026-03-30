package com.piyushprajpti.feature.fees.payment

data class FeesPaymentState(
    val studentName: String = "",
    val studentId: String = "",
    val semester: String = "",
    val studentImage: String = "",
    val feeHeads: List<FeeHead> = emptyList(),
    val paymentMethods: List<PaymentMethod> = emptyList(),
    val selectedPaymentMethodId: String? = null,
    val overlayType: PaymentOverlayType? = null,
    val receiptInfo: ReceiptInfo? = null
) {
    val selectedFeeHeads = feeHeads.filter { it.isSelected }
    val totalAmount = selectedFeeHeads.sumOf { it.amount }
    val serviceTax = totalAmount * 0.02 // Example 2% tax
    val payableAmount = totalAmount + serviceTax
}

data class FeeHead(
    val id: String,
    val name: String,
    val amount: Double,
    val isSelected: Boolean = false
)

data class PaymentMethod(
    val id: String,
    val name: String,
    val icon: String // Using resource name or icon identifier
)

sealed interface PaymentOverlayType {
    data object Confirmation : PaymentOverlayType
    data object Success : PaymentOverlayType
}

data class ReceiptInfo(
    val receiptNo: String,
    val dateAndTime: String,
    val paidVia: String
)
