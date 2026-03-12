package com.piyushprajpti.data.repository.auth

import com.piyushprajpti.core.http.Result
import com.piyushprajpti.data.local.dao.auth.TenantDao
import com.piyushprajpti.data.local.entity.auth.toDomain
import com.piyushprajpti.data.local.entity.auth.toEntity
import com.piyushprajpti.data.remote.response.toDomain
import com.piyushprajpti.data.remote.service.auth.AuthApiService
import com.piyushprajpti.domain.model.auth.Tenant
import com.piyushprajpti.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthRepositoryImpl(
    private val apiService: AuthApiService,
    private val tenantDao: TenantDao
) : AuthRepository {
    override suspend fun getTenantList(): Result<List<Tenant>> {
        return when (val result = apiService.getTenants()) {
            is Result.Success -> {
                val tenants = result.data.data.tenants.map { it.toDomain() }
                Result.Success(tenants)
            }

            is Result.Error -> Result.Error(result.error)
        }
    }

    override suspend fun saveTenant(tenant: Tenant): Result<Boolean> {
        return try {
            tenantDao.upsertTenant(tenant.toEntity())
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(
                com.piyushprajpti.core.http.ApiException(
                    error = com.piyushprajpti.core.http.ErrorCode.UNKNOWN,
                    message = e.message ?: "Failed to save tenant"
                )
            )
        }
    }

    override fun getSavedTenant(): Flow<Tenant?> {
        return tenantDao.getSavedTenant().map { it?.toDomain() }
    }
}
