package com.piyushprajpti.data.local.dao.auth

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.piyushprajpti.data.local.entity.auth.TenantEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TenantDao {

    @Upsert
    suspend fun upsertTenant(tenant: TenantEntity)

    @Query("SELECT * FROM tenants LIMIT 1")
    fun getSavedTenant(): Flow<TenantEntity?>
}
