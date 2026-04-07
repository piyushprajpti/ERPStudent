package com.piyushprajpti.data.remote.service.user

import com.piyushprajpti.core.constants.ApiConstants
import com.piyushprajpti.core.http.ApiResponseHelper
import com.piyushprajpti.core.http.Result
import com.piyushprajpti.data.remote.response.user.UserResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class UserApiService(private val httpClient: HttpClient) {
    suspend fun getUserProfile(): Result<UserResponse> {
        return ApiResponseHelper.handleApiResponse {
            httpClient.get(ApiConstants.User.PROFILE)
        }
    }
}
