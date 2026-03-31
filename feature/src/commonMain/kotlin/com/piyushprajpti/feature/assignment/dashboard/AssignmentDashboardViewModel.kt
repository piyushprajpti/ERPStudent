package com.piyushprajpti.feature.assignment.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class AssignmentDashboardViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _assignments = MutableStateFlow<List<Assignment>>(emptyList())
    private val _selectedTab = MutableStateFlow(AssignmentTab.Pending)

    val state = combine(_assignments, _selectedTab) { assignments, selectedTab ->
        val filtered = when (selectedTab) {
            AssignmentTab.Pending -> assignments.filter { 
                it.status == AssignmentStatus.Upcoming || it.status == AssignmentStatus.Overdue 
            }
            AssignmentTab.Submitted -> assignments.filter { it.status == AssignmentStatus.Submitted }
            AssignmentTab.Graded -> assignments.filter { it.status == AssignmentStatus.Graded }
        }
        
        AssignmentDashboardState(
            assignments = filtered,
            selectedTab = selectedTab,
            overdueCount = assignments.count { it.status == AssignmentStatus.Overdue },
            upcomingThisWeekCount = 3 // This could be calculated from dates in a real app
        )
    }.onStart {
        loadAssignments()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = AssignmentDashboardState()
    )

    fun onEvent(event: AssignmentDashboardEvent) {
        when (event) {
            is AssignmentDashboardEvent.OnTabSelected -> {
                _selectedTab.update { event.tab }
            }
        }
    }

    private fun loadAssignments() {
        val dummyAssignments = listOf(
            Assignment(
                id = "1",
                title = "DBMS - Mini Project",
                assignedBy = "Dr. Sarah Chen",
                weightage = 25,
                dueDate = "Oct 12, 2023",
                status = AssignmentStatus.Overdue,
                daysLate = 2
            ),
            Assignment(
                id = "2",
                title = "Data Structures - Assignment 4",
                assignedBy = "Prof. Alan Smith",
                weightage = 15,
                dueDate = "Oct 15, 2023",
                status = AssignmentStatus.Upcoming,
                daysLeft = 4
            ),
            Assignment(
                id = "3",
                title = "Theory of Computation",
                assignedBy = "Dr. Emily Ray",
                weightage = 10,
                dueDate = "Oct 18, 2023",
                status = AssignmentStatus.Upcoming,
                daysLeft = 7
            ),
            Assignment(
                id = "4",
                title = "Computer Networks",
                assignedBy = "Dr. Robert Brown",
                weightage = 20,
                dueDate = "Oct 20, 2023",
                status = AssignmentStatus.Upcoming,
                daysLeft = 9
            ),
            Assignment(
                id = "5",
                title = "Operating Systems Lab",
                assignedBy = "Prof. Alice Wilson",
                weightage = 5,
                dueDate = "Oct 10, 2023",
                status = AssignmentStatus.Submitted
            ),
            Assignment(
                id = "6",
                title = "Software Engineering",
                assignedBy = "Dr. Michael Scott",
                weightage = 15,
                dueDate = "Sep 25, 2023",
                status = AssignmentStatus.Graded,
                grade = "A+",
                feedback = "Excellent documentation and code structure."
            ),
            Assignment(
                id = "7",
                title = "Artificial Intelligence",
                assignedBy = "Dr. James Bond",
                weightage = 30,
                dueDate = "Oct 25, 2023",
                status = AssignmentStatus.Upcoming,
                daysLeft = 14
            ),
            Assignment(
                id = "8",
                title = "Compiler Design",
                assignedBy = "Prof. Xavier",
                weightage = 12,
                dueDate = "Oct 05, 2023",
                status = AssignmentStatus.Submitted
            ),
            Assignment(
                id = "9",
                title = "Machine Learning",
                assignedBy = "Dr. Andrew Ng",
                weightage = 40,
                dueDate = "Oct 30, 2023",
                status = AssignmentStatus.Upcoming,
                daysLeft = 19
            ),
            Assignment(
                id = "10",
                title = "Mobile App Development",
                assignedBy = "Dr. Piyush Prajapati",
                weightage = 20,
                dueDate = "Oct 08, 2023",
                status = AssignmentStatus.Overdue,
                daysLate = 6
            ),
            Assignment(
                id = "11",
                title = "Cloud Computing",
                assignedBy = "Prof. Sarah Jenkins",
                weightage = 15,
                dueDate = "Oct 22, 2023",
                status = AssignmentStatus.Upcoming,
                daysLeft = 11
            ),
            Assignment(
                id = "12",
                title = "Cyber Security",
                assignedBy = "Dr. Kevin Mitnick",
                weightage = 25,
                dueDate = "Sep 20, 2023",
                status = AssignmentStatus.Graded,
                grade = "B",
                feedback = "Good effort, but needs more focus on encryption algorithms."
            )
        )
        _assignments.update { dummyAssignments }
    }
}
