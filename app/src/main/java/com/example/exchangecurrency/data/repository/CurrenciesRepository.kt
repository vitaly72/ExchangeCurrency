package com.example.exchangecurrency.data.repository

import com.example.exchangecurrency.data.api.CurrencyApi
import com.example.exchangecurrency.domain.model.CurrenciesNBU
import com.example.exchangecurrency.domain.model.ResponsePB
import com.example.exchangecurrency.domain.repository.ICurrenciesRepository
import io.reactivex.Single
import org.jetbrains.annotations.NotNull

class CurrenciesRepository constructor(
    val currencyApi: CurrencyApi
) : ICurrenciesRepository {

    override fun currenciesPB(@NotNull date: String): Single<ResponsePB> =
        currencyApi.currenciesPB(date)

    override fun currenciesNBU(date: String): Single<CurrenciesNBU> =
        currencyApi.currenciesNBU(date)
}