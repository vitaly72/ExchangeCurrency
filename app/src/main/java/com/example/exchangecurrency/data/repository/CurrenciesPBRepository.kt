package com.example.exchangecurrency.data.repository

import com.example.exchangecurrency.data.api.CurrencyApi
import com.example.exchangecurrency.domain.model.BasePB
import com.example.exchangecurrency.domain.model.CurrencyPB
import com.example.exchangecurrency.domain.repository.ICurrenciesPBRepository
import retrofit2.Call

class CurrenciesPBRepository(private val currencyApi: CurrencyApi) : ICurrenciesPBRepository {
    override fun currenciesPB(date: String): Call<BasePB<CurrencyPB>> {
        return currencyApi.currenciesPB(date)
    }
}