package com.piyushprajpti.feature.assignment.dashboard

sealed interface AssignmentDashboardEvent {
    data class OnTabSelected(val tab: AssignmentTab) : AssignmentDashboardEvent
}