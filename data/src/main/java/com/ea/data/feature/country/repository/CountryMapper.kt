package com.ea.data.feature.country.repository

import com.ea.country.CountryDetailsQuery
import com.ea.country.CountryListQuery
import com.ea.data.util.Mapper
import com.ea.domain.model.country.CountryDO

class CountryListMapper: Mapper<CountryListQuery.Country,CountryDO> {
    override fun mapFromEntity(type: CountryListQuery.Country): CountryDO {
        return CountryDO(
            code = type.code(),
            name = type.name()
        )
    }
}

class CountryDetailMapper: Mapper<CountryDetailsQuery.Country,CountryDO> {
    override fun mapFromEntity(type: CountryDetailsQuery.Country): CountryDO {
        return CountryDO(
            code = type.code(),
            name = type.name(),
            capital = type.capital(),
            currency = type.currency(),
            emoji = type.emoji(),
            native = type.native_()
        )
    }
}