package com.piyushprajpti.feature.settings.profile

data class ProfileState(
    val profileData: StudentProfile? = null
)

data class StudentProfile(
    val name: String,
    val enrollmentNo: String,
    val courseTag: String,
    val profileImage: String? = null,
    val basicInfo: List<ProfileItem>,
    val academicDetails: List<ProfileItem>,
    val contactDetails: List<ProfileItem>,
    val guardianDetails: List<ProfileItem>
)

data class ProfileItem(
    val label: String,
    val value: String,
    val isFullWidth: Boolean = false,
    val icon: ProfileIcon? = null
)

enum class ProfileIcon {
    Phone, Email, Location
}
