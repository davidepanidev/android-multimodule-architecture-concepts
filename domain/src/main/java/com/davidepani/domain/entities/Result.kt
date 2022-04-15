package com.davidepani.domain.entities

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val value: T) : Result<T>()
    data class Failure(val error: Throwable) : Result<Nothing>()
}
