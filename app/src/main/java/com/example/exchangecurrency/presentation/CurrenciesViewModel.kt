package com.example.exchangecurrency.presentation

import android.app.DatePickerDialog
import android.content.Context
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exchangecurrency.data.api.RetrofitClient.retrofitService
import com.example.exchangecurrency.data.repository.CurrenciesNBURepository
import com.example.exchangecurrency.data.repository.CurrenciesPBRepository
import com.example.exchangecurrency.domain.model.CurrencyNBU
import com.example.exchangecurrency.domain.model.CurrencyPB
import com.example.exchangecurrency.domain.usecase.pb.IGetCurrenciesPBUseCase
import com.example.exchangecurrency.util.Constants
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import java.text.SimpleDateFormat
import java.util.*

class CurrenciesViewModel : ViewModel(), Observable {
    private var currentDatePB = SimpleDateFormat("dd.MM.yyyy", Locale.US)
    private var currentDateNBU = SimpleDateFormat("yyyyMMdd", Locale.US)
    private val compositeDisposable = CompositeDisposable()
    val isLoad = MutableLiveData<Boolean>()
    val positionToScroll = MutableLiveData<Int>()
    val currencyNBUList = MutableLiveData<List<CurrencyNBU>>()
    var calendar: Calendar = Calendar.getInstance()

    @Bindable
    val date = MutableLiveData<String>()

    @Bindable
    val currencyEur = MutableLiveData<CurrencyPB>()

    @Bindable
    val currencyUsd = MutableLiveData<CurrencyPB>()

    @Bindable
    val currencyRub = MutableLiveData<CurrencyPB>()

    init {
        date.value = currentDatePB.format(Date())
        isLoad.value = false
    }

    fun loadCurrencies(date: String = currentDatePB.format(Date())) {
        val currenciesPBRepository = CurrenciesPBRepository(
            retrofitService(Constants.BASE_URL_PB)
        )
        currenciesPBRepository.currenciesPB(date).subscribeBy {
            isLoad.value = true
            currencyEur.value = it.exchangeRate.find { pb -> pb.currency == "EUR" }
            currencyUsd.value = it.exchangeRate.find { pb -> pb.currency == "USD" }
            currencyRub.value = it.exchangeRate.find { pb -> pb.currency == "RUB" }
        }.addTo(compositeDisposable)

        val currNBURepository = CurrenciesNBURepository(
            retrofitService(Constants.BASE_URL_NBU)
        )
        currNBURepository.currenciesNBU(date).subscribeBy {
            currencyNBUList.value = it
        }.addTo(compositeDisposable)
    }

    fun calendarButtonOnClick(context: Context) {
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.US)
                date.value = simpleDateFormat.format(calendar.time)
                loadCurrencies(currentDatePB.format(Date()))
            }
        val datePickerDialog = DatePickerDialog(
            context, dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.maxDate = Calendar.getInstance().timeInMillis;
        datePickerDialog.show()
    }

    public fun scrollToIndex(currency: String) {
        val item = currencyNBUList.value?.find { it.cc == currency }
        positionToScroll.value = currencyNBUList.value?.indexOf(item)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}