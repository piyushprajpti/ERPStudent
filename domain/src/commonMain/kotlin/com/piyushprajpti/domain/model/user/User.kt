package com.piyushprajpti.domain.model.user

data class User(
    val id: String,
    val tenantId: String,
    val userId: String,
    val rollNumber: String,
    val registrationNo: String,
    val admissionDate: String,
    val religion: String?,
    val caste: String?,
    val createdAt: String,
    val updatedAt: String,
    val userData: UserData,
    val guardians: List<Guardian>
)

data class UserData(
    val id: String,
    val name: String,
    val email: String,
    val phone: String?,
    val avatar: String?,
    val dob: String?,
    val gender: String?,
    val address: String?
)

data class Guardian(
    val id: String,
    val studentId: String,
    val name: String,
    val relation: String,
    val phone: String?,
    val email: String?
)
