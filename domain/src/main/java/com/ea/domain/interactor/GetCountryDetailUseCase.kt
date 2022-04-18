package com.ea.domain.interactor

import com.ea.domain.model.country.CountryDO
import com.ea.domain.model.wrapper.Result
import com.ea.domain.model.wrapper.ResultWrapper
import com.ea.domain.repository.CountryRepository
import javax.inject.Inject

class GetCountryDetailUseCase @Inject constructor(private val countryRepository: CountryRepository) :
    UseCase<GetCountryDetailUseCase.Params, CountryDO> {

    data class Params(val id: String)

    override suspend fun execute(params: Params): ResultWrapper<Result<CountryDO>> {
        return countryRepository.getCountryDetail(params.id)
    }


}