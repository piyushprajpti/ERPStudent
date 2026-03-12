package com.piyushprajpti.erpstudent

import androidx.compose.ui.window.ComposeUIViewController
import com.piyushprajpti.erpstudent.db.RoomDBFactory

fun MainViewController() = ComposeUIViewController {
    val db = RoomDBFactory().createDatabase()
    App() }