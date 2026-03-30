package com.piyushprajpti.feature.leave.leave_history

data class LeaveHistoryState(
    val isLoading: Boolean = false,
    val selectedStatus: LeaveStatus = LeaveStatus.All,
    val leaves: List<LeaveHistory> = emptyList(),
    val filteredLeaves: List<LeaveHistory> = emptyList()
)

enum class LeaveStatus(val label: String) {
    All("All"),
    Pending("Pending"),
    Approved("Approved"),
    Rejected("Rejected")
}

data class LeaveHistory(
    val id: String,
    val type: String,
    val status: LeaveStatus,
    val submittedDate: String,
    val startDate: String,
    val endDate: String,
    val totalDays: Int,
    val reason: String,
    val rejectionRemark: String? = null
)