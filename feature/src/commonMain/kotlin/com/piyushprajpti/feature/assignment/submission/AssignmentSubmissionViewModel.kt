package com.piyushprajpti.feature.assignment.submission

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class AssignmentSubmissionViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(AssignmentSubmissionState())
    val state = _state.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = AssignmentSubmissionState()
    )

    init {
        loadAssignmentDetails()
    }

    private fun loadAssignmentDetails() {
        _state.update {
            it.copy(
                assignment = AssignmentDetail(
                    id = "2",
                    title = "Data Structures - Assignment 4",
                    description = "Implement a circular queue using an array. Ensure all edge cases like overflow and underflow are handled. Include time complexity analysis.",
                    marks = 100,
                    dueDate = "Oct 15, 2026",
                    allowedFormats = listOf("PDF", "DOCX", "PNG"),
                    imageUrl = "https://images.unsplash.com/photo-1517694712202-14dd9538aa97?q=80&w=2070&auto=format&fit=crop"
                ),
                previousSubmission = PreviousSubmission(
                    date = "Oct 12, 2026",
                    time = "02:45 PM"
                ),
                selectedFiles = listOf(
                    SelectedFile("CircularQueue_Implementation.pdf", "1.2 MB"),
                    SelectedFile("Flowchart_Algorithm.png", "450 KB")
                )
            )
        }
    }

    fun onEvent(event: AssignmentSubmissionEvent) {
        when (event) {
            is AssignmentSubmissionEvent.OnSubmissionTypeChanged -> {
                _state.update { it.copy(submissionType = event.type) }
            }
            is AssignmentSubmissionEvent.OnCommentChanged -> {
                _state.update { it.copy(comments = event.comment) }
            }
            is AssignmentSubmissionEvent.OnTextChanged -> {
                _state.update { it.copy(textContent = event.text) }
            }
            is AssignmentSubmissionEvent.OnLinkChanged -> {
                _state.update { it.copy(linkContent = event.link) }
            }
            is AssignmentSubmissionEvent.OnRemoveFile -> {
                _state.update { s ->
                    s.copy(selectedFiles = s.selectedFiles.filter { it != event.file })
                }
            }
            AssignmentSubmissionEvent.OnSubmit -> {
                messageHelper.showMessage("Assignment submitted successfully!")
            }
            AssignmentSubmissionEvent.OnChooseFile -> {
                // In a real app, this would open a file picker
            }
            AssignmentSubmissionEvent.OnViewPreviousSubmission -> {
                // View previous submission logic
            }
        }
    }
}
