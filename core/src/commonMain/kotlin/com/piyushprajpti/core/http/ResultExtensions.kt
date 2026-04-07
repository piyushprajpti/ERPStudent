package com.piyushprajpti.core.http

suspend fun <T : Any, R : Any> Result<T>.map(transform: suspend (T) -> R): Result<R> {
    return when (this) {
        is Result.Success -> Result.Success(transform(data))
        is Result.Error -> Result.Error(error)
    }
}

suspend fun <T : Any> Result<T>.onSuccess(action: suspend (T) -> Unit): Result<T> {
    if (this is Result.Success) action(data)
    return this
}

suspend fun <T : Any> Result<T>.onError(action: suspend (ApiException) -> Unit): Result<T> {
    if (this is Result.Error) action(error)
    return this
}
