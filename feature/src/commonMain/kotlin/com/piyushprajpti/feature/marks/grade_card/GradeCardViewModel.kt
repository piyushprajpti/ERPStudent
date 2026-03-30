package com.piyushprajpti.feature.marks.grade_card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class GradeCardViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(GradeCardState())
    val state = _state.onStart {
        loadDummyData()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = GradeCardState()
    )

    fun onEvent(event: GradeCardEvent) {
        when (event) {
            GradeCardEvent.DownloadPdf -> {
                messageHelper.showMessage("Downloading Grade Card...")
            }
            GradeCardEvent.Share -> {
                messageHelper.showMessage("Sharing Grade Card...")
            }
        }
    }

    private fun loadDummyData() {
        _state.update {
            it.copy(
                studentInfo = StudentInfo(
                    name = "Arjun Mehta",
                    enrollment = "2024CS42",
                    course = "B.Tech CS",
                    semester = "4"
                ),
                semesterResult = SemesterResult(
                    semesterTitle = "Semester IV - Result Card",
                    systemName = "Academic Management System",
                    subjects = listOf(
                        SubjectResult("Data Structures", 4, 28, 56, 84, "A+", "orange"),
                        SubjectResult("Operating Systems", 4, 25, 52, 77, "A", "orange"),
                        SubjectResult("Discrete Math", 3, 29, 60, 89, "O", "orange"),
                        SubjectResult("Comp. Networks", 4, 22, 48, 70, "B+", "orange"),
                        SubjectResult("Web Tech", 2, 27, 54, 81, "A+", "orange")
                    ),
                    sgpa = "3.8",
                    cgpa = "3.7",
                    status = "PASS"
                ),
                performanceOverview = listOf(
                    PerformancePoint("DS", 0.84f),
                    PerformancePoint("OS", 0.77f),
                    PerformancePoint("DM", 0.89f),
                    PerformancePoint("CN", 0.70f),
                    PerformancePoint("WT", 0.81f)
                )
            )
        }
    }
}
