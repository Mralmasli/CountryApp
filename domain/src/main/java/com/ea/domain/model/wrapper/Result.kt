package com.ea.domain.model.wrapper

data class Result<T>(
    val data: T? = null,
    val message: String? = null
)