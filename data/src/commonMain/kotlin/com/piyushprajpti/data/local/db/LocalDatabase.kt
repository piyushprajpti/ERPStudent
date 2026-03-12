@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.piyushprajpti.data.local.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.piyushprajpti.data.local.dao.auth.TenantDao
import com.piyushprajpti.data.local.entity.auth.TenantEntity

@Database(entities = [TenantEntity::class], version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun tenantDao(): TenantDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<LocalDatabase> {
    override fun initialize(): LocalDatabase
}

const val dbFileName = "erp_student.db"
