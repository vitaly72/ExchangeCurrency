package com.example.exchangecurrency.data.api

import com.example.exchangecurrency.domain.model.BasePB
import com.example.exchangecurrency.domain.model.CurrencyPB
import com.example.exchangecurrency.domain.model.CurrencyNBU
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {
    @GET("exchange_rates?json")
    fun currenciesPB(@Query("date") date: String): Call<BasePB<CurrencyPB>>

    @GET("exchange?")
    fun currenciesNBU(@Query("date") date: String,
                      @Query("json") json: String = ""): Call<List<CurrencyNBU>>
}