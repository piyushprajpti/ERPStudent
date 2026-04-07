package com.piyushprajpti.data.local.db

import androidx.room.TypeConverter
import com.piyushprajpti.data.local.entity.user.GuardianEntity
import kotlinx.serialization.json.Json

class Converters {
    @TypeConverter
    fun fromGuardianList(value: List<GuardianEntity>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toGuardianList(value: String): List<GuardianEntity> {
        return Json.decodeFromString(value)
    }
}
