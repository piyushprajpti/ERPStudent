package com.piyushprajpti.feature.marks.overview

data class MarksOverviewState(
    val selectedPeriod: AcademicPeriod? = null,
    val academicPeriods: List<AcademicPeriod> = emptyList(),
    val cgpa: Float = 0f,
    val maxCgpa: Float = 4.0f,
    val percentile: String = "",
    val subjects: List<Subject> = emptyList(),
    val aggregatePercentage: Float = 0f
)

data class AcademicPeriod(
    val id: String,
    val name: String,
    val isCurrent: Boolean = false
)

data class Subject(
    val id: String,
    val name: String,
    val code: String,
    val professor: String,
    val grade: String,
    val midtermMarks: MarkInfo,
    val assignmentMarks: MarkInfo,
    val endTermMarks: MarkInfo
)

data class MarkInfo(
    val label: String,
    val score: Int,
    val total: Int
)
