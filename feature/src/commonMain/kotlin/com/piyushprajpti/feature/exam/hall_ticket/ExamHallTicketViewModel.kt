package com.piyushprajpti.feature.exam.hall_ticket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class ExamHallTicketViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(ExamHallTicketState())
    val state = _state.onStart {
        loadHallTicketData()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ExamHallTicketState()
    )

    private fun loadHallTicketData() {
        _state.update {
            it.copy(
                studentName = "Arjun Mehta",
                studentId = "2024CS42",
                course = "B.Tech Computer Science",
                semester = "Semester 4",
                academicYear = "2025-26",
                profileImage = "https://cdn-icons-png.flaticon.com/512/15297/15297661.png",
                examTitle = "Official Hall Ticket",
                examSubtitle = "SEMESTER IV MIDTERM 2026",
                examsRemaining = 3,
                schedules = listOf(
                    HallTicketSchedule(
                        id = "1",
                        month = "MAR",
                        day = "25",
                        subject = "Data Structures",
                        time = "09:00 AM",
                        location = "Hall B-4",
                        seatNo = "42"
                    ),
                    HallTicketSchedule(
                        id = "2",
                        month = "MAR",
                        day = "27",
                        subject = "Operating Systems",
                        time = "10:30 AM",
                        location = "Lab 2",
                        seatNo = "12"
                    ),
                    HallTicketSchedule(
                        id = "3",
                        month = "MAR",
                        day = "30",
                        subject = "Computer Networks",
                        time = "09:00 AM",
                        location = "Hall A-1",
                        seatNo = "08"
                    )
                ),
                qrContent = "https://img.freepik.com/premium-vector/modern-square-sample-black-qr-code-icon-white-background_131186-1524.jpg",
                verificationCode = "VER-AMS-2026-Xo82",
                instructions = listOf(
                    "Students must carry this hall ticket along with a valid College ID Card.",
                    "Reporting time is 30 minutes before the commencement of examination.",
                    "Electronic gadgets, including smartwatches, are strictly prohibited in the hall.",
                    "Follow the seating arrangement as displayed at the entrance of the examination block."
                )
            )
        }
    }

    fun onEvent(event: ExamHallTicketEvent) {
        when (event) {
            ExamHallTicketEvent.OnDownloadPdf -> {
                messageHelper.showMessage("Downloading Hall Ticket...")
            }
            ExamHallTicketEvent.OnShare -> {
                messageHelper.showMessage("Sharing Hall Ticket...")
            }
        }
    }
}
