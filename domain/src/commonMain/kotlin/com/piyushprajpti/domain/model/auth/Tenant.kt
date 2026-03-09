package com.piyushprajpti.domain.model.auth

data class Tenant(
    val id: String,
    val name: String,
    val slug: String,
    val domain: String?,
    val logo: String?,
    val address: String?,
    val contactEmail: String,
    val contactPhone: String?,
    val createdAt: String,
    val updatedAt: String
)