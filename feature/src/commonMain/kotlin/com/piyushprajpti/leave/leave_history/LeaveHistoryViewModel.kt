package com.piyushprajpti.leave.leave_history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.feature.components.loading_helper.LoadingHelper
import com.piyushprajpti.feature.components.message_helper.MessageHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class LeaveHistoryViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(LeaveHistoryState())
    val state = _state.onStart {
        loadLeaves()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = LeaveHistoryState()
    )

    private fun loadLeaves() {
        val dummyLeaves = listOf(
            LeaveHistory(
                id = "1",
                type = "Sick Leave",
                status = LeaveStatus.Pending,
                submittedDate = "Mar 08",
                startDate = "Mar 10",
                endDate = "Mar 12",
                totalDays = 3,
                reason = "Severe flu and high fever. Doctor advised complete bed rest for 3 days to recover..."
            ),
            LeaveHistory(
                id = "2",
                type = "Casual Leave",
                status = LeaveStatus.Approved,
                submittedDate = "Feb 20",
                startDate = "Feb 22",
                endDate = "Feb 23",
                totalDays = 2,
                reason = "Attending sister's wedding ceremony in hometown. Tickets booked for the weekend."
            ),
            LeaveHistory(
                id = "3",
                type = "Personal Leave",
                status = LeaveStatus.Rejected,
                submittedDate = "Jan 12",
                startDate = "Jan 15",
                endDate = "Jan 15",
                totalDays = 1,
                reason = "Exam week scheduled. Leave during exams is not permitted without emergency medical proof.",
                rejectionRemark = "Exam week scheduled. Leave during exams is not permitted without emergency medical proof."
            )
        )
        _state.update { it.copy(leaves = dummyLeaves, filteredLeaves = dummyLeaves) }
    }

    fun onEvent(event: LeaveHistoryEvent) {
        when (event) {
            is LeaveHistoryEvent.OnStatusSelected -> {
                _state.update { 
                    val filtered = if (event.status == LeaveStatus.All) {
                        it.leaves
                    } else {
                        it.leaves.filter { leave -> leave.status == event.status }
                    }
                    it.copy(selectedStatus = event.status, filteredLeaves = filtered)
                }
            }
            LeaveHistoryEvent.OnAddLeaveClicked -> {
                // Navigate to Add Leave Screen
            }
            LeaveHistoryEvent.OnBackClicked -> {
                // Handle back navigation
            }
        }
    }
}