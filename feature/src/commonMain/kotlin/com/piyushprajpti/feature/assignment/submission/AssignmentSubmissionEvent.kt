package com.piyushprajpti.feature.assignment.submission

sealed interface AssignmentSubmissionEvent {
    data class OnSubmissionTypeChanged(val type: SubmissionType) : AssignmentSubmissionEvent
    data class OnCommentChanged(val comment: String) : AssignmentSubmissionEvent
    data class OnTextChanged(val text: String) : AssignmentSubmissionEvent
    data class OnLinkChanged(val link: String) : AssignmentSubmissionEvent
    data class OnRemoveFile(val file: SelectedFile) : AssignmentSubmissionEvent
    data object OnSubmit : AssignmentSubmissionEvent
    data object OnChooseFile : AssignmentSubmissionEvent
    data object OnViewPreviousSubmission : AssignmentSubmissionEvent
}