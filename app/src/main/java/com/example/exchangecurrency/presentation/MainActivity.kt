package com.example.exchangecurrency.presentation

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exchangecurrency.databinding.ActivityMainBinding
import com.example.exchangecurrency.databinding.CurrencyLineBinding
import com.example.exchangecurrency.domain.model.Currency
import com.example.exchangecurrency.domain.model.CurrencyNBU
import com.example.exchangecurrency.domain.model.CurrencyPB
import com.example.exchangecurrency.presentation.extension.observe
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var layoutManager: LinearLayoutManager
    private val viewModel: CurrenciesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        with(viewModel) {
            observe(currencyEur, ::onCurrencyEur)
            observe(currencyUsd, ::onCurrencyUsd)
            observe(currencyRub, ::onCurrencyRub)
        }
        setListeners()
    }

    private fun onCurrencyEur(model: CurrencyPB?) {
        model ?: return

        onCurrencyLine(model, binding.eurCurrencyLayout)
    }

    private fun onCurrencyUsd(model: CurrencyPB?) {
        model ?: return

        onCurrencyLine(model, binding.usdCurrencyLayout)
    }

    private fun onCurrencyRub(model: CurrencyPB?) {
        model ?: return

        onCurrencyLine(model, binding.rubCurrencyLayout)
    }

    private fun onCurrencyLine(model: CurrencyPB, binding: CurrencyLineBinding) {
        with(binding) {
            tvCurrency.text = model.saleRateNB
            eurPurchaseTextView.text = model.purchaseRate.substring(0, 6)
            tvCurrency.text = model.saleRate.substring(0, 6)
        }
    }

    private fun setListeners() {
        with(binding) {
            datePBTextView.setOnClickListener {
                calendarButtonOnClick(applicationContext)
            }
            eurCurrencyLayout.root.setOnClickListener {
                viewModel.scrollToIndex(Currency.EUR)
            }
            usdCurrencyLayout.root.setOnClickListener {
                viewModel.scrollToIndex(Currency.USD)
            }
            rubCurrencyLayout.root.setOnClickListener {
                viewModel.scrollToIndex(Currency.RUB)
            }
        }
    }

    private fun initRecyclerView(list: List<CurrencyNBU>) {
        layoutManager = LinearLayoutManager(this)
        with(binding.nbuCurrenciesRecyclerView) {
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
            adapter = RecyclerAdapter(list)
        }
    }

    private fun calendarButtonOnClick(context: Context) {
        val calendar: Calendar = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.US)
                viewModel.date.value = simpleDateFormat.format(calendar.time)
                viewModel.loadCurrencies(viewModel.currentDatePB.format(Date()))
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
}