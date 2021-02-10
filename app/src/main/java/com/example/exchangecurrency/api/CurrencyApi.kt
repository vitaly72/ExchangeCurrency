package com.example.exchangecurrency.api

import com.example.exchangecurrency.model.BasePB
import com.example.exchangecurrency.model.CurrencyPB
import com.example.exchangecurrency.model.CurrencyNBU
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