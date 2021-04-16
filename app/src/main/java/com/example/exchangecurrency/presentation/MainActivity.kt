package com.example.exchangecurrency.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exchangecurrency.R
import com.example.exchangecurrency.databinding.ActivityMainBinding
import com.example.exchangecurrency.domain.model.ResponsePB
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var currentDatePB = SimpleDateFormat("dd.MM.yyyy", Locale.US)
    var currentDateNBU = SimpleDateFormat("yyyyMMdd", Locale.US)
    private lateinit var currencyList: ResponsePB
    private lateinit var binding: ActivityMainBinding
    private lateinit var layoutManager: LinearLayoutManager
//    private val currenciesPBRepository: ICurrenciesPBRepository =
//        CurrenciesPBRepository(Common.retrofitService)
//    private val getCurrenciesPBUseCase: IGetCurrenciesPBUseCase =
//        GetCurrenciesPBUseCase(currenciesPBRepository)
//    private val currenciesViewModel: CurrenciesViewModel = CurrenciesViewModel(getCurrenciesPBUseCase)
    private val currenciesViewModel: CurrenciesViewModel by viewModels()

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = currenciesViewModel
        binding.lifecycleOwner = this

        currenciesViewModel.loadCurrencies()
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

    private fun initAllValuePB(currency: ResponsePB) {
        currencyList = currency
//        setValuePB("EUR", binding.eurPurchaseTextView, binding.eurSaleTextView)
//        setValuePB("USD", binding.usdPurchaseTextView, binding.usdSaleTextView)
//        setValuePB("RUB", binding.rubPurchaseTextView, binding.rubSaleTextView)
    }
}