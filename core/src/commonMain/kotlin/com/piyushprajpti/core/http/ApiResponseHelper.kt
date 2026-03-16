package com.piyushprajpti.core.http

import androidx.datastore.core.IOException
import co.touchlab.kermit.Logger
import io.ktor.client.call.body
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlin.coroutines.cancellation.CancellationException

object ApiResponseHelper {
    suspend inline fun <reified T : Any> handleApiResponse(
        crossinline block: suspend () -> HttpResponse
    ): Result<T> {
        return try {
            val response = block()
            if (response.status.isSuccess()) {
                val data: T = response.body()
                Logger.d(tag = "TAG") { "success response -> $data" }
                Result.Success(data)
            } else {
                val errorBody = response.body<String>()
                val errorMessage = try {
                    val json = Json.parseToJsonElement(errorBody).jsonObject
                    json["message"]?.jsonPrimitive?.content ?: "Something went wrong"
                } catch (e: Exception) {
                    "Error: ${response.status.value}"
                }

                val apiException = ApiException(
                    error = when (response.status.value) {
                        401 -> ErrorCode.AUTH_ERROR
                        404 -> ErrorCode.NOT_FOUND
                        else -> ErrorCode.UNKNOWN
                    },
                    message = errorMessage
                )
                Logger.e(tag = "TAG") { "error response status -> ${response.status.value} : $errorMessage" }
                Result.Error(apiException)
            }
        } catch (exception: ApiException) {
            Logger.e(tag = "TAG") { "api exception -> ${exception}" }
            Result.Error(exception)
        } catch (exception: Exception) {
            if (exception is CancellationException) throw exception
            Logger.e(tag = "TAG") { "error response -> ${exception}" }
            val apiException = when (exception) {
                is ConnectTimeoutException, is IOException -> ApiException(
                    error = ErrorCode.NO_INTERNET,
                    message = "No internet connection. Please check your network and try again."
                )

                else -> ApiException(
                    error = ErrorCode.UNKNOWN,
                    message = exception.message ?: "Something went wrong"
                )
            }
            Result.Error(apiException)
        }
    }
}
