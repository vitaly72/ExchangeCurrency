package com.example.exchangecurrency.domain.usecase

import com.example.exchangecurrency.domain.model.ResponsePB
import com.example.exchangecurrency.domain.repository.ICurrenciesPBRepository
import io.reactivex.Single
import javax.inject.Inject

class GetCurrenciesPBUseCase @Inject constructor(
    private val currenciesPBRepository: ICurrenciesPBRepository
) : IGetCurrenciesPBUseCase {
    //    override fun invoke(date: String): Result<ResponsePB> = currenciesPBRepository.currenciesPB(date)
    override fun invoke(date: String): Single<ResponsePB> =
        currenciesPBRepository.currenciesPB(date)
}