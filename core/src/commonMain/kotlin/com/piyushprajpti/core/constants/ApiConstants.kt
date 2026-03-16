package com.piyushprajpti.core.constants

object ApiConstants {
    const val BASE_URL = "https://ams-backend-cyan.vercel.app/api/"

    object Auth {
        const val TENANTS = "tenants"
        const val LOGIN = "auth/login"
    }

    object User {
        const val PROFILE = "user/profile"
    }

    object Common {
        const val SUPPORT = "support"

        const val FORGOT_PASSWORD = "auth/forgot-password"
        const val VERIFY_OTP = "auth/verify-otp"
        const val RESET_PASSWORD = "auth/reset-password"
    }
}
