package com.piyushprajpti.feature.exam.schedule

data class ExamScheduleState(
    val academicYear: String = "2024-25",
    val session: String = "March 2025",
    val examTypes: List<String> = listOf("Midterm", "End Term", "Practical", "Viva"),
    val selectedExamType: String = "Midterm",
    val exams: List<Exam> = emptyList(),
    val summary: ExamSummary = ExamSummary()
)

data class Exam(
    val id: String,
    val title: String,
    val type: String,
    val date: String,
    val time: String,
    val location: String,
    val reportingTime: String? = null,
    val invigilator: String? = null,
    val seatNo: String? = null,
    val isToday: Boolean = false,
    val daysRemaining: Int? = null
)

data class ExamSummary(
    val title: String = "",
    val totalExams: Int = 0,
    val completedExams: Int = 0,
    val remainingExams: Int = 0
)
