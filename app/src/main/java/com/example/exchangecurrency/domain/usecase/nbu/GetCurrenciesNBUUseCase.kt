package com.example.exchangecurrency.domain.usecase.nbu

import com.example.exchangecurrency.domain.model.CurrencyNBU
import com.example.exchangecurrency.domain.repository.ICurrenciesNBURepository
import io.reactivex.Single

class GetCurrenciesNBUUseCase constructor(
    private val currenciesNBURepository: ICurrenciesNBURepository
): IGetCurrenciesNBUUseCase {
    override operator fun invoke(date: String): Single<List<CurrencyNBU>> =
        currenciesNBURepository.currenciesNBU(date)
}