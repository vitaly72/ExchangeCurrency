package com.example.exchangecurrency

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.graphics.Paint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exchangecurrency.api.RetrofitClient
import com.example.exchangecurrency.databinding.ActivityMainBinding
import com.example.exchangecurrency.model.BasePB
import com.example.exchangecurrency.model.CurrencyNBU
import com.example.exchangecurrency.model.CurrencyPB
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var currentDatePB = SimpleDateFormat("dd.MM.yyyy", Locale.US)
    var currentDateNBU = SimpleDateFormat("yyyyMMdd", Locale.US)
    private lateinit var currencyList: BasePB<CurrencyPB>
    private lateinit var currencyNBUList: List<CurrencyNBU>
    private lateinit var binding: ActivityMainBinding
    private lateinit var layoutManager: LinearLayoutManager
    var calendar: Calendar? = null

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.datePBTextView.text = currentDatePB.format(Date())
        binding.datePBTextView.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        enqueueCurrencyPB(currentDatePB.format(Date()))
        enqueueCurrencyNBU(currentDateNBU.format(Date()))

        binding.calendarPBButton.setOnClickListener {
            calendarButtonOnClick()
        }
        binding.datePBTextView.setOnClickListener {
            calendarButtonOnClick()
        }
        //nbu list
        layoutManager = LinearLayoutManager(this)
        binding.nbuCurrenciesRecyclerView.layoutManager = layoutManager
        binding.nbuCurrenciesRecyclerView.setHasFixedSize(true)
        binding.nbuCurrenciesRecyclerView.isNestedScrollingEnabled = false
        //pb item
        binding.eurCurrencyLayout.setOnClickListener {
            val index = scrollToIndex("EUR")
        }
        binding.usdCurrencyLayout.setOnClickListener {
            scrollToIndex("USD")
        }
        binding.rubCurrencyLayout.setOnClickListener {
            scrollToIndex("RUB")
        }
    }

    private fun calendarButtonOnClick() {
        if (calendar == null) calendar = Calendar.getInstance()
        else {
            val dateSetListener =
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    calendar!!.set(Calendar.YEAR, year)
                    calendar!!.set(Calendar.MONTH, monthOfYear)
                    calendar!!.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.US)
                    binding.datePBTextView.text = simpleDateFormat.format(calendar!!.time)
                    binding.datePBTextView.paintFlags = Paint.UNDERLINE_TEXT_FLAG
                    enqueueCurrencyPB(currentDatePB.format(Date()))
                    enqueueCurrencyNBU(currentDateNBU.format(calendar!!.time))
                }
            val datePickerDialog = DatePickerDialog(
                this@MainActivity, dateSetListener,
                calendar!!.get(Calendar.YEAR),
                calendar!!.get(Calendar.MONTH),
                calendar!!.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.datePicker.maxDate = Calendar.getInstance().timeInMillis;
            datePickerDialog.show()
        }
    }

    private fun enqueueCurrencyPB(date: String) {
        val currencyApi = RetrofitClient.retrofitService(RetrofitClient.BASE_URL_PB)
        val currencies: Call<BasePB<CurrencyPB>> = currencyApi.currenciesPB(date)

        currencies.enqueue(object : Callback<BasePB<CurrencyPB>> {
            override fun onResponse(
                call: Call<BasePB<CurrencyPB>>,
                response: Response<BasePB<CurrencyPB>>
            ) {
                if (response.isSuccessful) {
                    println("response.body: ${response.body()}")
                    currencyList = response.body()!!
                    println("response.size: ${currencyList.exchangeRate.size}")
                    //
                    setValuePB("EUR", binding.eurPurchaseTextView, binding.eurSaleTextView)
                    setValuePB("USD", binding.usdPurchaseTextView, binding.usdSaleTextView)
                    setValuePB("RUB", binding.rubPurchaseTextView, binding.rubSaleTextView)
                } else {
                    println("response is not successful")
                }
            }

            override fun onFailure(call: Call<BasePB<CurrencyPB>>, t: Throwable) {
                println("failture: $t")
            }
        })
    }

    private fun enqueueCurrencyNBU(date: String) {
        val currencyApi = RetrofitClient.retrofitService(RetrofitClient.BASE_URL_NBU)
        val currencies: Call<List<CurrencyNBU>> = currencyApi.currenciesNBU(date)

        currencies.enqueue(object : Callback<List<CurrencyNBU>> {
            override fun onResponse(
                call: Call<List<CurrencyNBU>>,
                response: Response<List<CurrencyNBU>>
            ) {
                currencyNBUList = response.body()!!
                binding.nbuCurrenciesRecyclerView.adapter = RecyclerAdapter(currencyNBUList)
            }

            override fun onFailure(call: Call<List<CurrencyNBU>>, t: Throwable) {
                println("nbu onFailure: $t")
            }
        })
    }

    private fun setValuePB(currency: String, purchaseTextView: TextView, saleTextView: TextView) {
        val item = currencyList.exchangeRate.find { it.currency == currency }
        purchaseTextView.text = item?.purchaseRate.toString().substring(0, 6)
        saleTextView.text = item?.saleRate.toString().substring(0, 6)
    }

    private fun scrollToIndex(currency: String): Int {
        val item = currencyNBUList.find { it.cc == currency }
        val index = currencyNBUList.indexOf(item)
        layoutManager.scrollToPositionWithOffset(index, 0)

        return index;
    }
}