package com.example.exchangecurrency.presentation

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exchangecurrency.databinding.ActivityMainBinding
import com.example.exchangecurrency.domain.model.CurrencyNBU
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var layoutManager: LinearLayoutManager
    private val viewModel: CurrenciesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        viewModel.currencyNBUList.observe(this) {
            initRecyclerView(it)
        }
        binding.datePBTextView.setOnClickListener {
            calendarButtonOnClick(applicationContext)
        }

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