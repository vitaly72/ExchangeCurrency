package com.example.exchangecurrency.domain.model

/**
 * Created by vitaliy on 09.04.2022
 */
data class CurrencyUiModel(
    val currenciesEUR: CurrencyPB,
    val currenciesUSD: CurrencyPB,
    val currenciesRUB: CurrencyPB,
    val currenciesNBU: CurrenciesNBU,
)