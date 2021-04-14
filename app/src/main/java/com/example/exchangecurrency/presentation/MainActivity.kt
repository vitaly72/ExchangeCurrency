package com.example.exchangecurrency.presentation

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.graphics.Paint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exchangecurrency.R
import com.example.exchangecurrency.databinding.ActivityMainBinding
import com.example.exchangecurrency.domain.model.BasePB
import com.example.exchangecurrency.domain.model.CurrencyNBU
import com.example.exchangecurrency.domain.model.CurrencyPB
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var currentDatePB = SimpleDateFormat("dd.MM.yyyy", Locale.US)
    var currentDateNBU = SimpleDateFormat("yyyyMMdd", Locale.US)
    var calendar: Calendar? = null
    private lateinit var currencyList: BasePB<CurrencyPB>
    private lateinit var currencyNBUList: List<CurrencyNBU>
    private lateinit var binding: ActivityMainBinding
    private lateinit var layoutManager: LinearLayoutManager
    private val currenciesViewModel: CurrenciesViewModel = CurrenciesViewModel()

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = currenciesViewModel
        binding.lifecycleOwner = this
        binding.datePBTextView.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        currenciesViewModel.loadCurrencies(currentDatePB.format(Date()))
        currenciesViewModel.currenciesReceivedLiveData.observe(this, {
            it?.let {
                initAllValuePB(it)
            }
        })

//        //nbu list
//        layoutManager = LinearLayoutManager(this)
//        binding.nbuCurrenciesRecyclerView.layoutManager = layoutManager
//        binding.nbuCurrenciesRecyclerView.setHasFixedSize(true)
//        binding.nbuCurrenciesRecyclerView.isNestedScrollingEnabled = false
//        //pb item
//        binding.eurCurrencyLayout.setOnClickListener {
//            scrollToIndex("EUR")
//        }
//        binding.usdCurrencyLayout.setOnClickListener {
//            scrollToIndex("USD")
//        }
//        binding.rubCurrencyLayout.setOnClickListener {
//            scrollToIndex("RUB")
//        }
    }

    private fun enqueueCurrencyNBU(date: String) {
//        val currencyApi = RetrofitClient.retrofitService(RetrofitClient.BASE_URL_NBU)
//        val currencies: Call<List<CurrencyNBU>> = currencyApi.currenciesNBU(date)
//
//        currencies.enqueue(object : Callback<List<CurrencyNBU>> {
//            override fun onResponse(
//                call: Call<List<CurrencyNBU>>,
//                response: Response<List<CurrencyNBU>>
//            ) {
//                currencyNBUList = response.body()!!
//                binding.nbuCurrenciesRecyclerView.adapter = RecyclerAdapter(currencyNBUList)
//            }
//
//            override fun onFailure(call: Call<List<CurrencyNBU>>, t: Throwable) {
//                println("nbu onFailure: $t")
//            }
//        })
    }

    private fun initAllValuePB(currency: BasePB<CurrencyPB>) {
        currencyList = currency
        setValuePB("EUR", binding.eurPurchaseTextView, binding.eurSaleTextView)
        setValuePB("USD", binding.usdPurchaseTextView, binding.usdSaleTextView)
        setValuePB("RUB", binding.rubPurchaseTextView, binding.rubSaleTextView)
    }

    private fun setValuePB(currency: String, purchaseTextView: TextView, saleTextView: TextView) {
        if (currencyList.exchangeRate.isEmpty()) {
            purchaseTextView.text = "000"
            saleTextView.text = "000"
        } else {
            val item = currencyList.exchangeRate.find { it.currency == currency }
            purchaseTextView.text = item?.purchaseRate.toString().substring(0, 6)
            saleTextView.text = item?.saleRate.toString().substring(0, 6)
        }
    }
}