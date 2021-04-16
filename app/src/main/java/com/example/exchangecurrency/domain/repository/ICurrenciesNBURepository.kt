package com.example.exchangecurrency.domain.repository

import com.example.exchangecurrency.domain.model.CurrencyNBU
import io.reactivex.Single

interface ICurrenciesNBURepository {
    fun currenciesNBU(date: String): Single<List<CurrencyNBU>>
}