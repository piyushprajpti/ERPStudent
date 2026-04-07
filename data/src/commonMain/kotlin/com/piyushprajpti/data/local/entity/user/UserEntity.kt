package com.piyushprajpti.data.local.entity.user

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.piyushprajpti.domain.model.user.Guardian
import com.piyushprajpti.domain.model.user.User
import com.piyushprajpti.domain.model.user.UserData
import kotlinx.serialization.Serializable

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: String,
    val tenantId: String,
    val userId: String,
    val rollNumber: String,
    val registrationNo: String,
    val admissionDate: String,
    val religion: String?,
    val caste: String?,
    val createdAt: String,
    val updatedAt: String,
    @Embedded(prefix = "user_data_") val userData: UserDataEntity,
    val guardians: List<GuardianEntity>
)

@Serializable
data class UserDataEntity(
    val id: String,
    val name: String,
    val email: String,
    val phone: String?,
    val avatar: String?,
    val dob: String?,
    val gender: String?,
    val address: String?
)

@Serializable
data class GuardianEntity(
    val id: String,
    val studentId: String,
    val name: String,
    val relation: String,
    val phone: String?,
    val email: String?
)

fun UserEntity.toDomain() = User(
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
    userData = userData.toDomain(),
    guardians = guardians.map { it.toDomain() }
)

fun UserDataEntity.toDomain() = UserData(
    id = id,
    name = name,
    email = email,
    phone = phone,
    avatar = avatar,
    dob = dob,
    gender = gender,
    address = address
)

fun GuardianEntity.toDomain() = Guardian(
    id = id,
    studentId = studentId,
    name = name,
    relation = relation,
    phone = phone,
    email = email
)

fun User.toEntity() = UserEntity(
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
    userData = userData.toEntity(),
    guardians = guardians.map { it.toEntity() }
)

fun UserData.toEntity() = UserDataEntity(
    id = id,
    name = name,
    email = email,
    phone = phone,
    avatar = avatar,
    dob = dob,
    gender = gender,
    address = address
)

fun Guardian.toEntity() = GuardianEntity(
    id = id,
    studentId = studentId,
    name = name,
    relation = relation,
    phone = phone,
    email = email
)
