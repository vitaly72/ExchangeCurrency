package com.example.exchangecurrency.domain.model

data class CurrencyPB(
    var baseCurrency: String,
    var currency: String,
    var saleRateNB: String,
    var saleRate: String,
    var purchaseRate: String
)