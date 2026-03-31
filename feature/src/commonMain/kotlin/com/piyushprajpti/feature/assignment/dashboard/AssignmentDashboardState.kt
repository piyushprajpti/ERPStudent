package com.piyushprajpti.feature.assignment.dashboard

data class AssignmentDashboardState(
    val isLoading: Boolean = false,
    val assignments: List<Assignment> = emptyList(),
    val selectedTab: AssignmentTab = AssignmentTab.Pending,
    val overdueCount: Int = 0,
    val upcomingThisWeekCount: Int = 0
)

data class Assignment(
    val id: String,
    val title: String,
    val assignedBy: String,
    val weightage: Int,
    val dueDate: String,
    val status: AssignmentStatus,
    val daysLeft: Int? = null,
    val daysLate: Int? = null,
    val grade: String? = null,
    val feedback: String? = null
)

enum class AssignmentStatus {
    Overdue, Upcoming, Submitted, Graded
}

enum class AssignmentTab {
    Pending, Submitted, Graded
}