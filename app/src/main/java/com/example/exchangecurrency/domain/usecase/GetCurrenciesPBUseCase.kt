package com.example.exchangecurrency.domain.usecase

import com.example.exchangecurrency.domain.model.BasePB
import com.example.exchangecurrency.domain.model.CurrencyPB
import com.example.exchangecurrency.domain.repository.ICurrenciesPBRepository
import retrofit2.Call

class GetCurrenciesPBUseCase(private val currenciesPBRepository: ICurrenciesPBRepository) {
    fun buildUseCaseSingle(date: String): Call<BasePB<CurrencyPB>> {
        return currenciesPBRepository.currenciesPB(date)
    }
}