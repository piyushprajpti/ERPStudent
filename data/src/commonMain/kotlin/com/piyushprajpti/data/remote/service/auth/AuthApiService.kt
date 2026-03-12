package com.piyushprajpti.data.remote.service.auth

import com.piyushprajpti.core.constants.ApiConstants
import com.piyushprajpti.core.http.ApiResponseHelper
import com.piyushprajpti.core.http.Result
import com.piyushprajpti.data.remote.response.TenantListResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class AuthApiService(private val httpClient: HttpClient) {
    suspend fun getTenants(): Result<TenantListResponse> {
        return ApiResponseHelper.handleApiResponse {
            httpClient.get(ApiConstants.Auth.TENANTS)
        }
    }
}
