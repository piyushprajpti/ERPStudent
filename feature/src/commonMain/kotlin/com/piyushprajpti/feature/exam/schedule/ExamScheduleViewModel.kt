package com.piyushprajpti.feature.exam.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class ExamScheduleViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(ExamScheduleState())
    val state = _state.onStart {
        loadInitialData()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ExamScheduleState()
    )

    private fun loadInitialData() {
        _state.update {
            it.copy(
                academicYear = "2024-25",
                session = "March 2025",
                summary = ExamSummary(
                    title = "Semester 4 Midterm — In Progress",
                    totalExams = 6,
                    completedExams = 2,
                    remainingExams = 4
                ),
                exams = getDummyExams("Midterm")
            )
        }
    }

    fun onEvent(event: ExamScheduleEvent) {
        when (event) {
            is ExamScheduleEvent.OnExamTypeSelected -> {
                _state.update { 
                    it.copy(
                        selectedExamType = event.type,
                        exams = getDummyExams(event.type)
                    ) 
                }
            }
        }
    }

    private fun getDummyExams(type: String): List<Exam> {
        return when (type) {
            "Midterm" -> listOf(
                Exam(
                    id = "1",
                    title = "Data Structures",
                    type = "Midterm Examination",
                    date = "March 25",
                    time = "09:00 AM",
                    location = "Hall B-4",
                    reportingTime = "08:30 AM",
                    invigilator = "Prof. Turing",
                    seatNo = "42",
                    isToday = true
                ),
                Exam(
                    id = "2",
                    title = "Operating Systems",
                    type = "Midterm Examination",
                    date = "March 27",
                    time = "10:30 AM",
                    location = "Lab 2",
                    daysRemaining = 2
                ),
                Exam(
                    id = "3",
                    title = "Computer Networks",
                    type = "Midterm Examination",
                    date = "March 30",
                    time = "09:00 AM",
                    location = "Hall A-1",
                    daysRemaining = 5
                ),
                Exam(
                    id = "4",
                    title = "Database Management",
                    type = "Midterm Examination",
                    date = "April 02",
                    time = "02:00 PM",
                    location = "Hall C-2",
                    daysRemaining = 8
                )
            )
            "End Term" -> listOf(
                Exam(
                    id = "5",
                    title = "Software Engineering",
                    type = "End Term Examination",
                    date = "May 15",
                    time = "10:00 AM",
                    location = "Main Hall",
                    daysRemaining = 45
                )
            )
            "Practical" -> listOf(
                Exam(
                    id = "6",
                    title = "Web Development Lab",
                    type = "Practical Exam",
                    date = "May 05",
                    time = "09:00 AM",
                    location = "Lab 4",
                    daysRemaining = 35
                )
            )
            else -> emptyList()
        }
    }
}
