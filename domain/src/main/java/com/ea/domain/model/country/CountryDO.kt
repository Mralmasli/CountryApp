package com.ea.domain.model.country

data class CountryDO(
    val capital: String? = null,
    val code: String? = null,
    val currency: String? = null,
    val emoji: String? = null,
    val languages: List<Language>? = null,
    val name: String? = null,
    val native: String? = null
)