package com.piyushprajpti.feature.leave.leave_history

sealed interface LeaveHistoryEvent {
    data class OnStatusSelected(val status: LeaveStatus) : LeaveHistoryEvent
    data object OnAddLeaveClicked : LeaveHistoryEvent
    data object OnBackClicked : LeaveHistoryEvent
}