package com.example.exchangecurrency.model

data class BasePB<T>(var date: String, var bank: String, var baseCurrency: Int,
                     var baseCurrencyLis: String, var exchangeRate: List<T>) {
}