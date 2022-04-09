package com.example.exchangecurrency.di

import com.example.exchangecurrency.BuildConfig
import com.example.exchangecurrency.data.api.CurrencyApi
import com.example.exchangecurrency.data.repository.CurrenciesRepository
import com.example.exchangecurrency.domain.repository.ICurrenciesRepository
import com.example.exchangecurrency.domain.usecase.GetCurrenciesUseCase
import com.example.exchangecurrency.presentation.CurrenciesViewModel
import com.example.exchangecurrency.util.Constants.BASE_URL_PB
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), BASE_URL_PB) }
    single { provideApiService(get()) }

    single<ICurrenciesRepository> {
        CurrenciesRepository(get())
    }
    single {
        GetCurrenciesUseCase(get())
    }
    viewModel {
        CurrenciesViewModel(get())
    }
}

private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
} else OkHttpClient.Builder().build()

private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    BASE_URL: String
): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

private fun provideApiService(retrofit: Retrofit): CurrencyApi =
    retrofit.create(CurrencyApi::class.java)