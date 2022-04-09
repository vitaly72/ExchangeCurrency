package com.example.exchangecurrency.domain.usecase

import com.example.exchangecurrency.domain.model.CurrencyUiModel
import com.example.exchangecurrency.domain.repository.ICurrenciesRepository
import io.reactivex.Single

class GetCurrenciesUseCase constructor(
    val currenciesRepository: ICurrenciesRepository
) {
    operator fun invoke(date: String): Single<CurrencyUiModel> {
        return Single.zip(
            currenciesRepository.currenciesPB(date),
            currenciesRepository.currenciesNBU(date)
        ) { currenciesPB, currenciesNBU ->
            CurrencyUiModel(
                currenciesEUR = currenciesPB.exchangeRate.find { pb -> pb.currency == "EUR" }!!,
                currenciesUSD = currenciesPB.exchangeRate.find { pb -> pb.currency == "USD" }!!,
                currenciesRUB = currenciesPB.exchangeRate.find { pb -> pb.currency == "RUB" }!!,
                currenciesNBU = currenciesNBU,
            )
        }
    }
}