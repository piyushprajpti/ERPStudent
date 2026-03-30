package com.piyushprajpti.feature.exam.schedule

sealed interface ExamScheduleEvent {
    data class OnExamTypeSelected(val type: String) : ExamScheduleEvent
}
