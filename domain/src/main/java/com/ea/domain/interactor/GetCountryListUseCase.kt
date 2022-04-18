package com.ea.domain.interactor

import com.ea.domain.model.country.CountryDO
import com.ea.domain.model.wrapper.Result
import com.ea.domain.model.wrapper.ResultWrapper
import com.ea.domain.repository.CountryRepository
import javax.inject.Inject

class GetCountryListUseCase @Inject constructor(private val countryRepository: CountryRepository) :
    UseCase<GetCountryListUseCase.EmptyRequest,List<CountryDO>> {

    object EmptyRequest

    override suspend fun execute(params: EmptyRequest): ResultWrapper<Result<List<CountryDO>>> {
        return countryRepository.getCountryList()
    }


}