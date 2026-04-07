package com.piyushprajpti.data.local.entity.auth

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.piyushprajpti.domain.model.auth.TenantUser

@Entity(tableName = "tenant_users")
data class TenantUserEntity(
    @PrimaryKey val id: String,
    val tenantId: String,
    val email: String,
    val name: String,
    val avatar: String?,
    val phone: String?,
    val dob: String?,
    val gender: String?,
    val address: String?,
    val createdAt: String,
    val updatedAt: String
)

fun TenantUserEntity.toDomain() = TenantUser(
    id = id,
    tenantId = tenantId,
    email = email,
    name = name,
    avatar = avatar,
    phone = phone,
    dob = dob,
    gender = gender,
    address = address,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun TenantUser.toEntity() = TenantUserEntity(
    id = id,
    tenantId = tenantId,
    email = email,
    name = name,
    avatar = avatar,
    phone = phone,
    dob = dob,
    gender = gender,
    address = address,
    createdAt = createdAt,
    updatedAt = updatedAt
)
