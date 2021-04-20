package com.example.exchangecurrency.data.repository

import com.example.exchangecurrency.data.api.CurrencyApi
import com.example.exchangecurrency.domain.model.ResponsePB
import com.example.exchangecurrency.domain.repository.ICurrenciesPBRepository
import io.reactivex.Single
import org.jetbrains.annotations.NotNull
import retrofit2.Call

class CurrenciesPBRepository constructor(
    private val currencyApi: CurrencyApi
) : ICurrenciesPBRepository {
    override fun currenciesPB(@NotNull date: String): Single<ResponsePB> {
        return currencyApi.currenciesPB(date)
    }
}