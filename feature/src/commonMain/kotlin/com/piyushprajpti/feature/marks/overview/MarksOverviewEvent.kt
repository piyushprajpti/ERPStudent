package com.piyushprajpti.feature.marks.overview

sealed interface MarksOverviewEvent {
    data class OnPeriodSelected(val period: AcademicPeriod) : MarksOverviewEvent
    data object OnViewGradeCardClick : MarksOverviewEvent
}
