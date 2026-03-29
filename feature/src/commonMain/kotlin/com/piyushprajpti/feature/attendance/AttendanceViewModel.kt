package com.piyushprajpti.feature.attendance

import androidx.lifecycle.ViewModel
import com.piyushprajpti.feature.attendance.components.AttendanceStatus
import com.piyushprajpti.feature.attendance.components.AttendanceTrend
import com.piyushprajpti.feature.attendance.components.SubjectAttendance
import com.piyushprajpti.feature.components.loading_helper.LoadingHelper
import com.piyushprajpti.feature.components.message_helper.MessageHelper
import com.piyushprajpti.feature.components.message_helper.model.Alert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.LocalDate

class AttendanceViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(AttendanceState())
    val state = _state.asStateFlow()

    init {
        loadDummyData()
    }

    private fun loadDummyData() {
        val today = LocalDate(2026, 3, 12)
        
        val subjects = listOf(
            SubjectAttendance("Data Structure and Algorithms", "CS401", "Prof. Alan Turing", 24, 26, AttendanceTrend.UP),
            SubjectAttendance("Operating Systems", "CS402", "Dr. Grace Hopper", 17, 25, AttendanceTrend.DOWN),
            SubjectAttendance("Database Management", "CS403", "Prof. Edgar Codd", 20, 26, AttendanceTrend.STABLE),
            SubjectAttendance("Computer Networks", "CS404", "Vint Cerf", 22, 25, AttendanceTrend.UP)
        )

        val attendanceRecords = mapOf(
            LocalDate(2026, 3, 2) to AttendanceStatus.PRESENT,
            LocalDate(2026, 3, 3) to AttendanceStatus.PRESENT,
            LocalDate(2026, 3, 4) to AttendanceStatus.ABSENT,
            LocalDate(2026, 3, 5) to AttendanceStatus.PRESENT,
            LocalDate(2026, 3, 6) to AttendanceStatus.LATE,
            LocalDate(2026, 3, 9) to AttendanceStatus.PRESENT,
            LocalDate(2026, 3, 10) to AttendanceStatus.PRESENT,
            LocalDate(2026, 3, 11) to AttendanceStatus.PRESENT, // Wait, color in image is blue? Maybe PRESENT/ABSENT/LATE/OTHER?
            // Image shows green for present, red for absent, yellow for late, blue for something else?
            // Let's stick to PRESENT, ABSENT, LATE. Blue might be "CANCELLED" or something.
            LocalDate(2026, 3, 11) to AttendanceStatus.PRESENT, 
            LocalDate(2026, 3, 12) to AttendanceStatus.PRESENT,
            LocalDate(2026, 3, 13) to AttendanceStatus.PRESENT,
            LocalDate(2026, 3, 16) to AttendanceStatus.PRESENT,
            LocalDate(2026, 3, 17) to AttendanceStatus.PRESENT,
            LocalDate(2026, 3, 18) to AttendanceStatus.PRESENT,
            LocalDate(2026, 3, 19) to AttendanceStatus.ABSENT,
            LocalDate(2026, 3, 20) to AttendanceStatus.PRESENT
        )

        _state.update {
            it.copy(
                subjectAttendanceList = subjects,
                attendanceRecords = attendanceRecords,
                overallAttendance = 88f,
                totalPresent = 42,
                totalAbsent = 8,
                totalLate = 4,
                selectedDate = today,
                semester = "8TH SEMESTER"
            )
        }
    }

    fun onEvent(event: AttendanceEvent) {
        when (event) {
            is AttendanceEvent.SelectDate -> {
                _state.update { it.copy(selectedDate = event.date) }
            }
            AttendanceEvent.ShowAttendanceInfo -> {
                messageHelper.setAlert(
                    Alert(
                        label = "Requirement",
                        message = "Minimum ${state.value.minAttendanceRequirement.toInt()}% attendance is required."
                    )
                )
            }
        }
    }
}
