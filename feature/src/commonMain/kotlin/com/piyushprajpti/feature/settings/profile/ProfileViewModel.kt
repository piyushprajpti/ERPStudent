package com.piyushprajpti.feature.settings.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.DateConverter
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import com.piyushprajpti.core.http.onError
import com.piyushprajpti.domain.model.user.User
import com.piyushprajpti.domain.usecase.user.GetUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _state = MutableStateFlow(ProfileState())
    val state = combine(
        _state,
        getUserUseCase.getLocalUser()
    ) { state, user ->
        state.copy(
            profileData = user?.toStudentProfile()
        )
    }.onStart {
        loadProfile()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ProfileState()
    )

    private fun loadProfile() {
        viewModelScope.launch {
            val isLocalEmpty = getUserUseCase.getLocalUser().first() == null
            if (isLocalEmpty) {
                loadingHelper.showLoading()
            }
            getUserUseCase.fetchFromServer().onError {
                messageHelper.showError(it.message)
            }
            if (isLocalEmpty) {
                loadingHelper.hideLoading()
            }
        }
    }

    private fun User.toStudentProfile(): StudentProfile {
        return StudentProfile(
            name = userData.name,
            profileImage = userData.avatar,
            enrollmentNo = registrationNo,
            courseTag = "B.TECH CS",
            basicInfo = listOf(
                ProfileItem("DOB", DateConverter.formatDate(userData.dob), isFullWidth = false),
                ProfileItem("GENDER", userData.gender ?: "N/A", isFullWidth = false),
                ProfileItem("RELIGION", religion ?: "N/A", isFullWidth = false),
                ProfileItem("CASTE", caste ?: "N/A", isFullWidth = false)
            ),
            academicDetails = listOf(
                ProfileItem("ROLL NO", rollNumber, isFullWidth = false),
                ProfileItem("REGISTRATION NO", registrationNo, isFullWidth = false),
                ProfileItem("ADMISSION DATE", DateConverter.formatDate(admissionDate), isFullWidth = true)
            ),
            contactDetails = listOf(
                ProfileItem("PHONE",userData.phone ?: "N/A",isFullWidth = true,icon = ProfileIcon.Phone),
                ProfileItem("EMAIL", userData.email, isFullWidth = true, icon = ProfileIcon.Email),
                ProfileItem("ADDRESS",userData.address ?: "N/A",isFullWidth = true,icon = ProfileIcon.Location)
            ),
            guardianDetails = guardians.flatMap { guardian ->
                listOf(
                    ProfileItem("NAME (${guardian.relation})", guardian.name, isFullWidth = true),
                    ProfileItem("CONTACT", guardian.phone ?: "N/A", isFullWidth = false),
                    ProfileItem("EMAIL", guardian.email ?: "N/A", isFullWidth = false)
                )
            }
        )
    }
}
