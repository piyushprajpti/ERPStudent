package com.piyushprajpti.data.repository

import com.piyushprajpti.core.http.Result
import com.piyushprajpti.core.http.map
import com.piyushprajpti.core.http.onSuccess
import com.piyushprajpti.data.local.dao.user.UserDao
import com.piyushprajpti.data.local.entity.user.toDomain
import com.piyushprajpti.data.local.entity.user.toEntity
import com.piyushprajpti.data.remote.response.user.toDomain
import com.piyushprajpti.data.remote.service.user.UserApiService
import com.piyushprajpti.domain.model.user.User
import com.piyushprajpti.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val apiService: UserApiService,
    private val userDao: UserDao
) : UserRepository {
    override suspend fun getUserProfile(): Result<User> {
        return apiService.getUserProfile().map { it.toDomain() }.onSuccess {
            saveUser(it)
        }
    }

    override fun getSavedUser(): Flow<User?> {
        return userDao.getUser().map { it?.toDomain() }
    }

    override suspend fun saveUser(user: User) {
        userDao.insertUser(user.toEntity())
    }
}