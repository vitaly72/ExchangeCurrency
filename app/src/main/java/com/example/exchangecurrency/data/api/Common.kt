package com.example.exchangecurrency.data.api

import com.example.exchangecurrency.util.Constants

object Common {
    val retrofitService: CurrencyApi
        get() = RetrofitClient.retrofitService(Constants.BASE_URL_PB)
}