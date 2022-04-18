package com.ea.data.feature.country.datasource

import com.apollographql.apollo.api.Response
import com.ea.country.CountryDetailsQuery
import com.ea.country.CountryListQuery

interface CountryRemoteDataSource {

    suspend fun queryCountryList(): Response<CountryListQuery.Data>

    suspend fun queryCountryDetail(id: String): Response<CountryDetailsQuery.Data>
}