package com.example.exchangecurrency.di

import com.example.exchangecurrency.data.api.CurrencyApi
import com.example.exchangecurrency.data.repository.CurrenciesPBRepository
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideCurrenciesPBRepository(currencyApi: CurrencyApi): CurrenciesPBRepository {
        return CurrenciesPBRepository(currencyApi)
    }
}