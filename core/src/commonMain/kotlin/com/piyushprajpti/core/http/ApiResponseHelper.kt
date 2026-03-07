package com.piyushprajpti.core.http

import androidx.datastore.core.IOException
import co.touchlab.kermit.Logger
import io.ktor.client.call.body
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.statement.HttpResponse
import kotlin.coroutines.cancellation.CancellationException

object ApiResponseHelper {
    suspend inline fun <reified T : Any> handleApiResponse(
        crossinline block: suspend () -> HttpResponse
    ): Result<T> {
        return try {
            val response = block()
            val data: T = response.body()
            Result.Success(data)
        } catch (exception: ApiException) {
            Logger.e(tag = "API_EXCEPTION") { exception.message }
            Result.Error(exception)
        } catch (exception: Exception) {
            if (exception is CancellationException) throw exception
            Logger.e(tag = "API_EXCEPTION") { "${exception.message}" }
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