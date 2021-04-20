package com.example.exchangecurrency.domain.usecase.nbu

import com.example.exchangecurrency.domain.model.CurrencyNBU
import io.reactivex.Single

interface IGetCurrenciesNBUUseCase {
    operator fun invoke(date: String): Single<List<CurrencyNBU>>
}