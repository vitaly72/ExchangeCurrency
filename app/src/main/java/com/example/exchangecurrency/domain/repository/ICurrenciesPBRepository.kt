package com.example.exchangecurrency.domain.repository

import com.example.exchangecurrency.domain.model.ResponsePB
import io.reactivex.Single
import org.jetbrains.annotations.NotNull
import retrofit2.Call

interface ICurrenciesPBRepository {
    fun currenciesPB(@NotNull date: String): Single<ResponsePB>
}