package com.example.exchangecurrency.domain.usecase.pb

import com.example.exchangecurrency.domain.model.ResponsePB
import io.reactivex.Single
import retrofit2.Call

interface IGetCurrenciesPBUseCase {
    operator fun invoke(date: String): Single<ResponsePB>
}