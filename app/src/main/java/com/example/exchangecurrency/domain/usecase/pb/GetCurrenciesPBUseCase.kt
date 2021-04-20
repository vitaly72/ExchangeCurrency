package com.example.exchangecurrency.domain.usecase.pb

import com.example.exchangecurrency.domain.model.ResponsePB
import com.example.exchangecurrency.domain.repository.ICurrenciesPBRepository
import io.reactivex.Single
import retrofit2.Call

class GetCurrenciesPBUseCase constructor(
    private val currenciesPBRepository: ICurrenciesPBRepository
): IGetCurrenciesPBUseCase {
    override operator fun invoke(date: String): Single<ResponsePB> =
        currenciesPBRepository.currenciesPB(date)
}