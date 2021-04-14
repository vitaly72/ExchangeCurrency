package com.example.exchangecurrency.presentation

import android.app.DatePickerDialog
import android.content.Context
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exchangecurrency.data.api.Common
import com.example.exchangecurrency.domain.model.BasePB
import com.example.exchangecurrency.domain.model.CurrencyPB
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class CurrenciesViewModel() : ViewModel(), Observable {
    private var currentDatePB = SimpleDateFormat("dd.MM.yyyy", Locale.US)
    val currenciesReceivedLiveData = MutableLiveData<BasePB<CurrencyPB>>()
    val isLoad = MutableLiveData<Boolean>()
    var calendar: Calendar
    @Bindable
    val date = MutableLiveData<String>()

    init {
        calendar = Calendar.getInstance()
        date.value = currentDatePB.format(Date())
        isLoad.value = false
    }

    fun loadCurrencies(date: String) {
        Common.retrofitService
            .currenciesPB(date)
            .enqueue(object : Callback<BasePB<CurrencyPB>> {
                override fun onResponse(
                    call: Call<BasePB<CurrencyPB>>,
                    response: Response<BasePB<CurrencyPB>>
                ) {
                    isLoad.value = true
                    currenciesReceivedLiveData.value = response.body()
                }

                override fun onFailure(call: Call<BasePB<CurrencyPB>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    fun calendarButtonOnClick(context: Context) {
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.US)
                date.value = simpleDateFormat.format(calendar.time)
//                    binding.datePBTextView.paintFlags = Paint.UNDERLINE_TEXT_FLAG
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