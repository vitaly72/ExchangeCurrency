package com.example.exchangecurrency.data.repository

import com.example.exchangecurrency.data.api.CurrencyApi
import com.example.exchangecurrency.domain.model.CurrencyNBU
import com.example.exchangecurrency.domain.repository.ICurrenciesNBURepository
import retrofit2.Call

class CurrenciesNBURepository(private val currencyApi: CurrencyApi) : ICurrenciesNBURepository {
    override fun currenciesNBU(date: String): Call<List<CurrencyNBU>> {
        return currencyApi.currenciesNBU(date)
    }
}