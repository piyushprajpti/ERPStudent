package com.piyushprajpti.feature.fees.summary

data class FeesSummaryState(
    val studentName: String = "",
    val studentDetails: String = "",
    val totalFee: String = "",
    val academicYear: String = "",
    val paidAmount: String = "",
    val pendingAmount: String = "",
    val overdueAmount: String = "",
    val payNowAmount: String = "",
    val fees: List<FeeItem> = emptyList(),
    val history: List<HistoryItem> = emptyList(),
    val breakdown: List<BreakdownItem> = emptyList(),
    val nextInstallment: String = "",
    val nextInstallmentDate: String = ""
)

data class FeeItem(
    val title: String,
    val dueDate: String,
    val isOverdue: Boolean = false
)

data class HistoryItem(
    val title: String,
    val amount: String,
    val date: String
)

data class BreakdownItem(
    val title: String,
    val amount: String
)