package com.example.exchangecurrency.domain.repository

import com.example.exchangecurrency.domain.model.CurrencyNBU
import retrofit2.Call

interface ICurrenciesNBURepository {
    fun currenciesNBU(date: String): Call<List<CurrencyNBU>>
}