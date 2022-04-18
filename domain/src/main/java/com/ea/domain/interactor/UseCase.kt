package com.ea.domain.interactor

import com.ea.domain.model.wrapper.Result
import com.ea.domain.model.wrapper.ResultWrapper


interface UseCase<in Params, T> {
    suspend fun execute(params: Params): ResultWrapper<Result<T>>
}