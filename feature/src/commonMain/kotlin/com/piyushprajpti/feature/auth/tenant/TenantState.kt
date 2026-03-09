package com.piyushprajpti.feature.auth.tenant

import com.piyushprajpti.domain.model.auth.Tenant

data class TenantState(
    val isLoading: Boolean = false,
    val tenantList: List<Tenant> = emptyList(),
    val selectedTenant: Tenant? = null,
    val error: String? = null,
    val searchQuery: String = ""
)