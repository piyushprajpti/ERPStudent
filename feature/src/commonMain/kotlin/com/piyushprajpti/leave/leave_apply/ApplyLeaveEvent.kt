package com.piyushprajpti.leave.leave_apply

sealed interface ApplyLeaveEvent {
    data class OnLeaveTypeChange(val leaveType: String) : ApplyLeaveEvent
    data class OnFromDateChange(val date: Long?) : ApplyLeaveEvent
    data class OnToDateChange(val date: Long?) : ApplyLeaveEvent
    data class OnReasonChange(val reason: String) : ApplyLeaveEvent
    data object OnSubmit : ApplyLeaveEvent
    data object OnBackClick : ApplyLeaveEvent
}