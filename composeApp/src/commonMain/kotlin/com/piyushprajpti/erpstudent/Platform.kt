package com.piyushprajpti.erpstudent

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform