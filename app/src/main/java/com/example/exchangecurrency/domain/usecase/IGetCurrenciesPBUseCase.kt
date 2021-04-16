package com.example.exchangecurrency.domain.usecase

import com.example.exchangecurrency.domain.model.ResponsePB
import io.reactivex.Single

interface IGetCurrenciesPBUseCase {
//    operator fun invoke(date: String): Result<ResponsePB>
    operator fun invoke(date: String): Single<ResponsePB>
}