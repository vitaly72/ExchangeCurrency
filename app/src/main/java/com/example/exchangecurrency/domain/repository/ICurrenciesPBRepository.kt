package com.example.exchangecurrency.domain.repository

import com.example.exchangecurrency.domain.model.BasePB
import com.example.exchangecurrency.domain.model.CurrencyPB
import io.reactivex.Single
import retrofit2.Call

interface ICurrenciesPBRepository {
    fun currenciesPB(date: String): Call<BasePB<CurrencyPB>>
}