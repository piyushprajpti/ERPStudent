package com.piyushprajpti.erpstudent.db

import com.piyushprajpti.data.local.db.LocalDatabase

expect class RoomDBFactory {
    fun createDatabase(): LocalDatabase
}