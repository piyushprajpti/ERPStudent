package com.piyushprajpti.feature.exam.hall_ticket

data class ExamHallTicketState(
    val studentName: String = "",
    val studentId: String = "",
    val course: String = "",
    val semester: String = "",
    val academicYear: String = "",
    val profileImage: String? = null,
    val examTitle: String = "",
    val examSubtitle: String = "",
    val examsRemaining: Int = 0,
    val schedules: List<HallTicketSchedule> = emptyList(),
    val qrContent: String = "",
    val verificationCode: String = "",
    val instructions: List<String> = emptyList()
)

data class HallTicketSchedule(
    val id: String,
    val month: String,
    val day: String,
    val subject: String,
    val time: String,
    val location: String,
    val seatNo: String
)
