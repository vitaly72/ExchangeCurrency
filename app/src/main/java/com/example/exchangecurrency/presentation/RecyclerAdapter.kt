package com.example.exchangecurrency.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangecurrency.R
import com.example.exchangecurrency.databinding.CurrencyItemBinding
import com.example.exchangecurrency.domain.model.CurrencyNBU

class RecyclerAdapter(private val currencies: List<CurrencyNBU>) :
    RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CurrencyItemBinding.inflate(inflater)

        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(currencies[position])
    }

    override fun getItemCount(): Int = currencies.size

    inner class RecyclerViewHolder(private val binding: CurrencyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: CurrencyNBU) {

            with(binding) {
                currencyNameTextView.text = item.txt
                currencyNameTextView.text = root.resources.getString(R.string.uah, item.rate)
                currencyValueTextView.text = item.cc
                if (currencies.indexOf(item) % 2 != 0) {
                    root.setBackgroundResource(R.color.green_light)
                } else {
                    root.setBackgroundResource(R.color.white)
                }
            }

        }
    }
}
