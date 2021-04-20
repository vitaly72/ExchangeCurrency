package com.example.exchangecurrency.di
//
//import androidx.databinding.library.BuildConfig
//import com.example.exchangecurrency.data.api.CurrencyApi
//import com.example.exchangecurrency.data.repository.CurrenciesNBURepository
//import com.example.exchangecurrency.data.repository.CurrenciesPBRepository
//import com.example.exchangecurrency.domain.repository.ICurrenciesNBURepository
//import com.example.exchangecurrency.domain.repository.ICurrenciesPBRepository
//import com.example.exchangecurrency.util.Constants
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import org.koin.dsl.module
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//val appModule = module {
//    single { provideOkHttpClient() }
//    single { provideRetrofit(get(), Constants.BASE_URL_PB) }
//    single { provideApiService(get()) }
//
//    single<ICurrenciesPBRepository> {
//        return@single CurrenciesPBRepository(get())
//    }
//    single<ICurrenciesNBURepository> {
//        return@single CurrenciesNBURepository(get())
//    }
//}
//
//private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
//    val loggingInterceptor = HttpLoggingInterceptor()
//    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//    OkHttpClient.Builder()
//        .addInterceptor(loggingInterceptor)
//        .build()
//} else OkHttpClient.Builder().build()
//
//private fun provideRetrofit(
//    okHttpClient: OkHttpClient,
//    BASE_URL: String
//): Retrofit =
//    Retrofit.Builder()
//        .addConverterFactory(GsonConverterFactory.create())
//        .baseUrl(BASE_URL)
//        .client(okHttpClient)
//        .build()
//
//private fun provideApiService(retrofit: Retrofit): CurrencyApi =
//    retrofit.create(CurrencyApi::class.java)