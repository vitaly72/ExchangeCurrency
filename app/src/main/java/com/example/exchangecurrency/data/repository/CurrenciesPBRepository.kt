package com.example.exchangecurrency.data.repository

import com.example.exchangecurrency.data.api.CurrencyApi
import com.example.exchangecurrency.domain.model.ResponsePB
import com.example.exchangecurrency.domain.repository.ICurrenciesPBRepository
import io.reactivex.Single

class CurrenciesPBRepository(private val currencyApi: CurrencyApi) : ICurrenciesPBRepository {
    override fun currenciesPB(date: String): Single<ResponsePB> {
//    override fun currenciesPB(date: String): Result<ResponsePB> {
        return currencyApi.currenciesPB(date)
    }
}