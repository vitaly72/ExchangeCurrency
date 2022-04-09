package com.example.exchangecurrency.domain.model

data class ResponsePB(
    val date: String,
    val bank: String,
    val baseCurrency: Int,
    val baseCurrencyLis: String,
    val exchangeRate: List<CurrencyPB>
)

data class CurrencyPB(
    val baseCurrency: String,
    val currency: String,
    val saleRateNB: String,
    val saleRate: String,
    val purchaseRate: String
)