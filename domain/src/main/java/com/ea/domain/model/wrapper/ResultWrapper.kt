package com.ea.domain.model.wrapper

sealed class ResultWrapper <out T> {
    data class Success <T>(val result: T): ResultWrapper<T>()
    data class Error <U>(val rawResponse: U): ResultWrapper<U>()
}