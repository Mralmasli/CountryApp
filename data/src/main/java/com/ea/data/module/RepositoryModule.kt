package com.ea.data.module

import com.apollographql.apollo.ApolloClient
import com.ea.data.feature.country.repository.CountryRepositoryImpl
import com.ea.domain.repository.CountryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun bindsCountryRepository(
        apolloClient: ApolloClient
    ): CountryRepository {
        return CountryRepositoryImpl(apolloClient)
    }
}