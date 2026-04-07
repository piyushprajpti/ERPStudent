@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.piyushprajpti.data.local.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import com.piyushprajpti.data.local.dao.auth.TenantDao
import com.piyushprajpti.data.local.dao.auth.TenantUserDao
import com.piyushprajpti.data.local.dao.user.UserDao
import com.piyushprajpti.data.local.entity.auth.TenantEntity
import com.piyushprajpti.data.local.entity.auth.TenantUserEntity
import com.piyushprajpti.data.local.entity.user.UserEntity

@Database(entities = [TenantEntity::class, TenantUserEntity::class, UserEntity::class], version = 4)
@TypeConverters(Converters::class)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun tenantDao(): TenantDao
    abstract fun tenantUserDao(): TenantUserDao
    abstract fun userDao(): UserDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<LocalDatabase> {
    override fun initialize(): LocalDatabase
}

const val dbFileName = "erp_student.db"
