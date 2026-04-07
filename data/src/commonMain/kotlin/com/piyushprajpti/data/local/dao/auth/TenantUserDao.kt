package com.piyushprajpti.data.local.dao.auth

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.piyushprajpti.data.local.entity.auth.TenantUserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TenantUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: TenantUserEntity)

    @Query("SELECT * FROM tenant_users LIMIT 1")
    fun getUser(): Flow<TenantUserEntity?>

    @Query("DELETE FROM tenant_users")
    suspend fun clearUser()
}
