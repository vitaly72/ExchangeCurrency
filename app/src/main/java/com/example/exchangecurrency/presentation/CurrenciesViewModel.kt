package com.example.exchangecurrency.presentation

import android.app.DatePickerDialog
import android.content.Context
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exchangecurrency.domain.model.CurrencyPB
import com.example.exchangecurrency.domain.model.ResponsePB
import com.example.exchangecurrency.domain.usecase.IGetCurrenciesPBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CurrenciesViewModel @Inject constructor(
    private val getCurrenciesPBUseCase: IGetCurrenciesPBUseCase
) : ViewModel(), Observable {
    private var currentDatePB = SimpleDateFormat("dd.MM.yyyy", Locale.US)
    val currenciesReceivedLiveData = MutableLiveData<ResponsePB>()
    val isLoad = MutableLiveData<Boolean>()
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
//        Common.retrofitService
//            .currenciesPB(date)
//            .enqueue(object : Callback<ResponsePB> {
//                override fun onResponse(
//                    call: Call<ResponsePB>,
//                    response: Response<ResponsePB>
//                ) {
//                    isLoad.value = true
//                    currenciesReceivedLiveData.value = response.body()
//                    currencyEur.value = response.body()
//                        ?.exchangeRate?.find { pb -> pb.currency == "EUR" }
//                    currencyUsd.value = response.body()
//                        ?.exchangeRate?.find { pb -> pb.currency == "USD" }
//                    currencyRub.value = response.body()
//                        ?.exchangeRate?.find { pb -> pb.currency == "RUB" }
//                }
//
//                override fun onFailure(call: Call<ResponsePB>, t: Throwable) {
//                    t.printStackTrace()
//                }
//            })
        val compositeDisposable = CompositeDisposable()
        getCurrenciesPBUseCase("").subscribeBy {
            isLoad.value = true
            currenciesReceivedLiveData.value = it
            currencyEur.value = it.exchangeRate.find { pb -> pb.currency == "EUR" }
            currencyUsd.value = it.exchangeRate.find { pb -> pb.currency == "USD" }
            currencyRub.value = it.exchangeRate.find { pb -> pb.currency == "RUB" }
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
//                    enqueueCurrencyNBU(currentDateNBU.format(calendar!!.time))
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

    fun scrollToIndex(currency: String) {
//        val item = currencyNBUList.find { it.cc == currency }
//        val index = currencyNBUList.indexOf(item)
//        layoutManager.scrollToPositionWithOffset(index, 0)
//
//        return index;
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}