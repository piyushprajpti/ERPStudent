package com.piyushprajpti.feature.fees.summary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class FeesSummaryViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(FeesSummaryState())
    val state = _state.onStart {
        loadDummyData()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = FeesSummaryState()
    )

    private fun loadDummyData() {
        _state.update {
            it.copy(
                studentName = "Arjun Mehta",
                studentDetails = "Semester 4 • ID: #AMS12345",
                totalFee = "₹5,700.00",
                academicYear = "Academic Year 2025-26",
                paidAmount = "₹4,500",
                pendingAmount = "₹1,200",
                overdueAmount = "₹300",
                payNowAmount = "₹1,500.00",
                fees = listOf(
                    FeeItem("Hostel Fee", "Due in 2 days", isOverdue = true),
                    FeeItem("Exam Fee", "Due in 15 days"),
                    FeeItem("Library Fine", "Overdue", isOverdue = true),
                    FeeItem("Lab Maintenance Fee", "Due in 30 days")
                ),
                history = listOf(
                    HistoryItem("Admission Fee", "₹2,000", "Jan 15, 2025"),
                    HistoryItem("Semester 3 Fee", "₹2,500", "Feb 10, 2025")
                ),
                breakdown = listOf(
                    BreakdownItem("Tuition Fee", "₹4,000"),
                    BreakdownItem("Development Fee", "₹1,000"),
                    BreakdownItem("Sports Fee", "₹500"),
                    BreakdownItem("Student Activity Fee", "₹200")
                ),
                nextInstallment = "Next installment reminder",
                nextInstallmentDate = "Due on May 15, 2026"
            )
        }
    }

    fun onEvent(event: FeesSummaryEvent) {
        when (event) {
            else -> {}
        }
    }
}