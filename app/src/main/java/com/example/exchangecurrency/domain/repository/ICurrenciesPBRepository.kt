package com.example.exchangecurrency.domain.repository

import com.example.exchangecurrency.domain.model.ResponsePB
import io.reactivex.Single

interface ICurrenciesPBRepository {
//    fun currenciesPB(date: String): Result<ResponsePB>
    fun currenciesPB(date: String): Single<ResponsePB>
}