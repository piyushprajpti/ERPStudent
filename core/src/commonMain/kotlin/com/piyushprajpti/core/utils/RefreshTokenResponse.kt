package com.piyushprajpti.core.utils

import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenResponse(
    val status: String,
    val data: RefreshTokenData
)

@Serializable
data class RefreshTokenData(
    val user: UserData,
    val accessToken: String,
    val refreshToken: String
)

@Serializable
data class UserData(
    val id: String,
    val tenantId: String,
    val email: String,
    val name: String,
    val avatar: String? = null,
    val phone: String? = null,
    val dob: String? = null,
    val gender: String? = null,
    val address: String? = null,
    val createdAt: String,
    val updatedAt: String,
    val roles: List<String> = emptyList()

)
