package com.piyushprajpti.data.remote.response.user

import com.piyushprajpti.domain.model.user.Guardian
import com.piyushprajpti.domain.model.user.User
import com.piyushprajpti.domain.model.user.UserData
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val status: String,
    val data: UserResponseData
)

@Serializable
data class UserResponseData(
    val student: UserDto
)

@Serializable
data class UserDto(
    val id: String,
    val tenantId: String,
    val userId: String,
    val rollNumber: String,
    val registrationNo: String,
    val admissionDate: String,
    val religion: String? = null,
    val caste: String? = null,
    val createdAt: String,
    val updatedAt: String,
    val user: UserDataDto,
    val guardians: List<GuardianDto> = emptyList()
)

@Serializable
data class UserDataDto(
    val id: String,
    val name: String,
    val email: String,
    val phone: String? = null,
    val avatar: String? = null,
    val dob: String? = null,
    val gender: String? = null,
    val address: String? = null
)

@Serializable
data class GuardianDto(
    val id: String,
    val studentId: String,
    val name: String,
    val relation: String,
    val phone: String? = null,
    val email: String? = null
)

fun UserResponse.toDomain() = data.student.toDomain()

fun UserDto.toDomain() = User(
    id = id,
    tenantId = tenantId,
    userId = userId,
    rollNumber = rollNumber,
    registrationNo = registrationNo,
    admissionDate = admissionDate,
    religion = religion,
    caste = caste,
    createdAt = createdAt,
    updatedAt = updatedAt,
    userData = user.toDomain(),
    guardians = guardians.map { it.toDomain() }
)

fun UserDataDto.toDomain() = UserData(
    id = id,
    name = name,
    email = email,
    phone = phone,
    avatar = avatar,
    dob = dob,
    gender = gender,
    address = address
)

fun GuardianDto.toDomain() = Guardian(
    id = id,
    studentId = studentId,
    name = name,
    relation = relation,
    phone = phone,
    email = email
)
