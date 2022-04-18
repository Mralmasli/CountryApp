package com.ea.domain.repository

import com.ea.domain.model.country.CountryDO
import com.ea.domain.model.wrapper.ResultWrapper
import com.ea.domain.model.wrapper.Result


interface CountryRepository {

    suspend fun getCountryList(): ResultWrapper<Result<List<CountryDO>>>

    suspend fun getCountryDetail(id:String): ResultWrapper<Result<CountryDO>>
}