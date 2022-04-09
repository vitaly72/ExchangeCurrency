package com.example.exchangecurrency.domain.repository

import com.example.exchangecurrency.domain.model.CurrenciesNBU
import com.example.exchangecurrency.domain.model.ResponsePB
import io.reactivex.Single

interface ICurrenciesRepository {

    fun currenciesPB(date: String): Single<ResponsePB>

    fun currenciesNBU(date: String): Single<CurrenciesNBU>
}