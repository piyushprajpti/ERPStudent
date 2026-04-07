package com.piyushprajpti.domain.repository

import com.piyushprajpti.core.http.Result
import com.piyushprajpti.domain.model.user.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUserProfile(): Result<User>
    fun getSavedUser(): Flow<User?>
    suspend fun saveUser(user: User)
}