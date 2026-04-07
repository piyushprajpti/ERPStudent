package com.piyushprajpti.domain.usecase.user

import com.piyushprajpti.core.http.Result
import com.piyushprajpti.domain.model.user.User
import com.piyushprajpti.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUserUseCase(
    private val repository: UserRepository
) {
    suspend fun fetchFromServer(): Result<User> {
        return repository.getUserProfile()
    }

    fun getLocalUser(): Flow<User?> {
        return repository.getSavedUser()
    }
}
