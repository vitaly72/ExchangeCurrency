package com.example.exchangecurrency.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exchangecurrency.domain.model.CurrencyNBU
import com.example.exchangecurrency.domain.model.CurrencyPB
import com.example.exchangecurrency.domain.usecase.GetCurrenciesUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import java.text.SimpleDateFormat
import java.util.*

class CurrenciesViewModel(
    val getCurrenciesUseCase: GetCurrenciesUseCase
) : ViewModel() {
    val currentDatePB = SimpleDateFormat("dd.MM.yyyy", Locale.US)
    private var currentDateNBU = SimpleDateFormat("yyyyMMdd", Locale.US)
    private val compositeDisposable = CompositeDisposable()
    private val isLoad = MutableLiveData(false)
    private val positionToScroll = MutableLiveData<Int>()
    val currencyNBUList = MutableLiveData<List<CurrencyNBU>>()
    val date = MutableLiveData(currentDatePB.format(Date()))
    val currencyEur = MutableLiveData<CurrencyPB>()
    val currencyUsd = MutableLiveData<CurrencyPB>()
    val currencyRub = MutableLiveData<CurrencyPB>()

    init {
        loadCurrencies()
    }

    fun loadCurrencies(date: String = currentDatePB.format(Date())) {
        getCurrenciesUseCase(date)
            .subscribeBy { model ->
                isLoad.value = true
                currencyEur.value = model.currenciesEUR
                currencyUsd.value = model.currenciesUSD
                currencyRub.value = model.currenciesRUB
                currencyNBUList.value = model.currenciesNBU
            }.addTo(compositeDisposable)
    }

    fun scrollToIndex(currency: String) {
        val item = currencyNBUList.value?.find { it.cc == currency }
        positionToScroll.value = currencyNBUList.value?.indexOf(item)
    }
}