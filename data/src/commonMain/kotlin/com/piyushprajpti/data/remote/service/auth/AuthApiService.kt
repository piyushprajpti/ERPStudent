package com.piyushprajpti.data.remote.service.auth

import com.piyushprajpti.core.constants.ApiConstants
import com.piyushprajpti.core.http.ApiResponseHelper
import com.piyushprajpti.core.http.Result
import com.piyushprajpti.data.remote.request.LoginRequest
import com.piyushprajpti.data.remote.response.LoginResponse
import com.piyushprajpti.data.remote.response.TenantListResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthApiService(private val httpClient: HttpClient) {
    suspend fun getTenants(): Result<TenantListResponse> {
        return ApiResponseHelper.handleApiResponse {
            httpClient.get(ApiConstants.Auth.TENANTS)
        }
    }

    suspend fun login(loginRequest: LoginRequest): Result<LoginResponse> {
        return ApiResponseHelper.handleApiResponse {
            httpClient.post(ApiConstants.Auth.LOGIN) {
                contentType(ContentType.Application.Json)
                setBody(loginRequest)
            }
        }
    }
}
