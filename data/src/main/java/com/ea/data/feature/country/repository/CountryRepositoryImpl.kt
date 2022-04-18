package com.ea.data.feature.country.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import com.ea.country.CountryDetailsQuery
import com.ea.country.CountryListQuery
import com.ea.domain.model.country.CountryDO
import com.ea.domain.model.wrapper.Result
import com.ea.domain.model.wrapper.ResultWrapper
import com.ea.domain.repository.CountryRepository
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val webServices: ApolloClient
) : CountryRepository {

    override suspend fun getCountryList(): ResultWrapper<Result<List<CountryDO>>> {
        return try {
            val result = webServices.query(CountryListQuery()).await()
            val listOfCountries = result.data?.countries()
                ?.map { country -> CountryListMapper().mapFromEntity(country) }
            ResultWrapper.Success(Result(listOfCountries ?: listOf()))
        } catch (ex: Exception) {
            ResultWrapper.Error(Result(message = ex.message))
        }
    }

    override suspend fun getCountryDetail(id: String): ResultWrapper<Result<CountryDO>> {
        return try {
            val result = webServices.query(CountryDetailsQuery(id)).await()
            val country = result.data?.country()
            country?.let {
                ResultWrapper.Success(Result(data = CountryDetailMapper().mapFromEntity(it)))
            }?: kotlin.run {
                ResultWrapper.Error(Result(message = "Error"))
            }

        } catch (ex: Exception) {
            ResultWrapper.Error(Result(message = ex.message))
        }
    }
}