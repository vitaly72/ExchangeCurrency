package com.example.exchangecurrency.domain.model

data class CurrenciesNBU(val list: List<CurrencyNBU>) : List<CurrencyNBU> by list

data class CurrencyNBU(
    val r030: String,
    val txt: String,
    val rate: Float,
    val cc: String,
    val exchangedate: String
)