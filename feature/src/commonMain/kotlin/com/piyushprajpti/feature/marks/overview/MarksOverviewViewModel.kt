package com.piyushprajpti.feature.marks.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class MarksOverviewViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _selectedPeriod = MutableStateFlow<AcademicPeriod?>(null)
    private val _academicPeriods = MutableStateFlow<List<AcademicPeriod>>(emptyList())
    private val _cgpaData = MutableStateFlow(3.74f)
    private val _percentile = MutableStateFlow("Top 5% of your class")
    private val _subjectsData = MutableStateFlow<Map<String, List<Subject>>>(emptyMap())

    val state = combine(
        _selectedPeriod,
        _academicPeriods,
        _cgpaData,
        _percentile,
        _subjectsData
    ) { selectedPeriod, academicPeriods, cgpa, percentile, subjectsMap ->
        val subjects = subjectsMap[selectedPeriod?.id] ?: emptyList()
        val aggregatePercentage = if (subjects.isNotEmpty()) {
            subjects.map { 
                (it.midtermMarks.score + it.assignmentMarks.score + it.endTermMarks.score).toFloat() / 
                (it.midtermMarks.total + it.assignmentMarks.total + it.endTermMarks.total) * 100 
            }.average().toFloat()
        } else 0f

        MarksOverviewState(
            selectedPeriod = selectedPeriod,
            academicPeriods = academicPeriods,
            cgpa = cgpa,
            percentile = percentile,
            subjects = subjects,
            aggregatePercentage = aggregatePercentage
        )
    }.onStart {
        loadData()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = MarksOverviewState()
    )

    private fun loadData() {
        val periods = listOf(
            AcademicPeriod("1", "Semester 1"),
            AcademicPeriod("2", "Semester 2"),
            AcademicPeriod("3", "Semester 3"),
            AcademicPeriod("4", "Semester 4 (Current)", isCurrent = true)
        )
        _academicPeriods.value = periods
        _selectedPeriod.value = periods.last()

        _subjectsData.value = mapOf(
            "4" to listOf(
                Subject(
                    "CS-401", "Database Management Systems", "CS-401", "Prof. Alan Turing", "A+",
                    MarkInfo("MIDTERM", 24, 30),
                    MarkInfo("ASSIGN.", 18, 20),
                    MarkInfo("END-TERM", 42, 50)
                ),
                Subject(
                    "CS-402", "Operating Systems", "CS-402", "Prof. Grace Hopper", "A",
                    MarkInfo("MIDTERM", 21, 30),
                    MarkInfo("ASSIGN.", 19, 20),
                    MarkInfo("END-TERM", 38, 50)
                ),
                Subject(
                    "CS-403", "Computer Networks", "CS-403", "Prof. Vint Cerf", "B",
                    MarkInfo("MIDTERM", 18, 30),
                    MarkInfo("ASSIGN.", 15, 20),
                    MarkInfo("END-TERM", 35, 50)
                ),
                Subject(
                    "CS-404", "Software Engineering", "CS-404", "Prof. Margaret Hamilton", "A-",
                    MarkInfo("MIDTERM", 23, 30),
                    MarkInfo("ASSIGN.", 17, 20),
                    MarkInfo("END-TERM", 40, 50)
                )
            ),
            "3" to listOf(
                Subject(
                    "CS-301", "Data Structures", "CS-301", "Prof. Donald Knuth", "A",
                    MarkInfo("MIDTERM", 25, 30),
                    MarkInfo("ASSIGN.", 19, 20),
                    MarkInfo("END-TERM", 45, 50)
                )
            )
        )
    }

    fun onEvent(event: MarksOverviewEvent) {
        when (event) {
            is MarksOverviewEvent.OnPeriodSelected -> {
                _selectedPeriod.value = event.period
            }
            MarksOverviewEvent.OnViewGradeCardClick -> {
                messageHelper.showMessage("Viewing Grade Card...")
            }
        }
    }
}
